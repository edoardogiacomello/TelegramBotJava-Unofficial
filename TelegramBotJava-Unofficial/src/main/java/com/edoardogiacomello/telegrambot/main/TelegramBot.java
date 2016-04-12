package com.edoardogiacomello.telegrambot.main;

import com.edoardogiacomello.telegrambot.jsonparser.OutputJsonParser;
import com.edoardogiacomello.telegrambot.methods.TelegramMethods;
import com.edoardogiacomello.telegrambot.types.*;
import com.edoardogiacomello.telegrambot.types.inline.results.InlineQueryResult;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represent the main interface of a telegram bot.
 * @author Edoardo Giacomello <edoardo.giacomello1990@gmail.com>
 *
 */
public class TelegramBot{
	private OutputJsonParser outputParser;
	private int lastUpdateId;
	private boolean autoConsumeUpdates;
	private TelegramEvents eventHandler;
	@SuppressWarnings("rawtypes")
	private ScheduledFuture scheduledPolling;
	private ScheduledExecutorService pollingExecutor;
	/**
	 * Instantiate a new Telegram Bot.
	 * @param token Auth token provided by "The Bot Father"
	 * @param autoConsumeUpdates setting it to false will not mark updates as confirmed, the getUpdates function will return the whole message history when called
	 */
	public TelegramBot(String token, boolean autoConsumeUpdates) {
		if (token==null || token.equals("")) throw new IllegalArgumentException("Auth Token must be not null");
		outputParser = new OutputJsonParser(token);
		lastUpdateId = 0;
		this.autoConsumeUpdates = autoConsumeUpdates;
	}
	
	/**
	 * Register an event handler for getting asynchronous updates whenever an update is received. 
	 * This method will fire the corresponding event according to the data type received
	 * @param handler
	 */
	public void registerForUpdates(TelegramEvents handler){
		eventHandler = handler;
	}
	
	/**
	 * Fetch updates from Telegram server. This overload is called with default parameters
	 * @return
	 */
	public synchronized List<Update> getUpdates(){return getUpdates(100,0);}
	
	/**
	 * Use this method to receive incoming updates using long polling (wiki). An Array of Update objects is returned.
	 * @param limit Limits the number of updates to be retrieved. Values between 1�100 are accepted. Defaults to 100
	 * @param timeout Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling
	 * @return
	 */
	public synchronized List<Update> getUpdates(int limit, int timeout){
		List<NameValuePair> paramsData = new ArrayList<NameValuePair>();
		if(autoConsumeUpdates) paramsData.add(new BasicNameValuePair("offset",Integer.toString(lastUpdateId+1)));
		if(limit > 0 && limit <=100) paramsData.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		if (timeout>=0) paramsData.add(new BasicNameValuePair("timeout", Integer.toString(timeout)));
		List<TelegramData> responseList = outputParser.request(TelegramMethods.getUpdates, paramsData);
		List <Update> updateList = new ArrayList<Update>();
		for (TelegramData currentResponse : responseList) {
			if (currentResponse instanceof Update){
				updateList.add((Update)currentResponse);
				lastUpdateId = ((Update)currentResponse).getUpdate_id();
				if(eventHandler != null) {
					eventHandler.onUpdate((Update)currentResponse);
					if(((Update)currentResponse).getMessage() != null) eventHandler.readMessage(((Update)currentResponse).getMessage());
                    if(((Update)currentResponse).getInlineQuery() != null) eventHandler.onInlineQueryReceived(((Update)currentResponse).getInlineQuery());
				}
				}
		}
		return updateList;
	}
	
	/**
	 * Retreive bot information in User form
	 * @return a User object containing info about the bot
	 */
	public User getMe(){
		List<TelegramData> response = outputParser.request(TelegramMethods.getMe, null);
		for (TelegramData currentResponse : response) {
			if (currentResponse instanceof User) return (User)currentResponse;
		}
		return null;
	}

	/**
	 * Use this method to send text messages in an easy way.
	 * This overload is slightly slower than the version with all the parameters.
	 * The parameters such as the web page preview, the parse mode and the reply markup are set to default values.
	 * @param message The message to send.
	 * @param replyToMessageId The message to reply to. Set to 0 if no reply is needed.
	 * @return On success, the sent Message is returned, on failure a null object is returned
	 */
	public Message sendMessage(Message message, int replyToMessageId) {
		return sendMessage(message.getChat().getChatId(), message.getText(), false, TelegramMethods.ParseMode.NONE, replyToMessageId, null);
	}

	/**
	 * Use this method to send text messages. Retro-compatibility overload.
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param text Text of the message to be sent
	 * @param disableWebPagePreview (Optional) Disables link previews for links in this message
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned, on failure a null object is returned
	 */
	public Message sendMessage(int chatId, String text, boolean disableWebPagePreview, int replyToMessageId, CustomReplyKeyboard replyMarkup) {
		return sendMessage(chatId, text, disableWebPagePreview, TelegramMethods.ParseMode.NONE, replyToMessageId, replyMarkup);
	}

		/**
         * Use this method to send text messages.
         * @param chatId Unique identifier for the message recipient — User or GroupChat id
         * @param text Text of the message to be sent
         * @param disableWebPagePreview (Optional) Disables link previews for links in this message
         * @param parseMode Set {@link com.edoardogiacomello.telegrambot.methods.TelegramMethods.ParseMode} to MARKDOWN or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message. Defaults to NONE.
         * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
         * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
         * @return On success, the sent Message is returned, on failure a null object is returned
         */
	public Message sendMessage(int chatId, String text, boolean disableWebPagePreview, TelegramMethods.ParseMode parseMode, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
		if(text == null || text.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId");
		//Building request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
		params.add(new BasicNameValuePair("text", text));
		if(disableWebPagePreview) params.add(new BasicNameValuePair("disable_web_page_preview", "true"));
		if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
		if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
		if(!parseMode.equals(TelegramMethods.ParseMode.NONE)) params.add (new BasicNameValuePair("parse_mode", parseMode.getValue()));
		//Making request
		List<TelegramData> responseList = outputParser.request(TelegramMethods.sendMessage, params);
		//Interpreting response
		for (TelegramData response : responseList) {
			if (response instanceof Message) return ((Message) response);
		}
		return null;	
	}
	
	/**
	 * Use this method to forward messages of any kind
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param fromChatId Unique identifier for the chat where the original message was sent — User or GroupChat id
	 * @param messageId Unique message identifier
	 * @return On success, the sent Message is returned, on failure a null object is returned
	 */
	public Message forwardMessage(int chatId, int fromChatId, int messageId){
		//Checking required parameters
				if(chatId == 0 || fromChatId==0 || messageId <=0) throw new IllegalArgumentException("You must specify at least a chatId");
				//Building request parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
				params.add(new BasicNameValuePair("from_chat_id",  Integer.toString(fromChatId)));
				params.add(new BasicNameValuePair("message_id",  Integer.toString(messageId)));
				//Making request
				List<TelegramData> responseList = outputParser.request(TelegramMethods.forwardMessage, params);
				//Interpreting response
				for (TelegramData response : responseList) {
					if (response instanceof Message) return ((Message) response);
				}
				return null;
	}
	
	/**
	 * Use this method to send photos
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param photo Photo to send. You can either pass a file_id as String to resend a photo that is already on the Telegram servers, or upload a new photo using multipart/form-data.
	 * @param caption (Optional) Photo caption (may also be used when resending photos by file_id).
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned,  on failure a null object is returned
	 */
	public Message sendPhoto(int chatId, String photo, String caption, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
				if(photo == null || photo.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId and a non-empty photo id");
				//Building request parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
				params.add(new BasicNameValuePair("photo", photo));
				if(!(caption.equals("") || caption == null)) params.add(new BasicNameValuePair("caption", caption));
				if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
				if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
				//Making request
				List<TelegramData> responseList = outputParser.request(TelegramMethods.sendPhoto, params);
				//Interpreting response
				for (TelegramData response : responseList) {
					if (response instanceof Message) return ((Message) response);
				}
				return null;	
	}
	
	/**
	 * Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message. For this to work, your audio must be in an .ogg file encoded with OPUS (other formats may be sent as Document). On success, the sent Message is returned. Bots can currently send audio files of up to 50 MB in size, this limit may be changed in the future.
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param audio Audio file to send. You can either pass a file_id as String to resend an audio that is already on the Telegram servers, or upload a new audio file using multipart/form-data.
	 * @param duration (Optional) Duration of sent audio in seconds
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned,  on failure a null object is returned
	 */
	public Message sendAudio(int chatId, String audio, int duration, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
		if(audio == null || audio.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId and a non-empty audio id");
		//Building request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
		params.add(new BasicNameValuePair("audio", audio));
		if(duration>0) params.add(new BasicNameValuePair("duration", Integer.toString(duration)));
		if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
		if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
		//Making request
		List<TelegramData> responseList = outputParser.request(TelegramMethods.sendAudio, params);
		//Interpreting response
		for (TelegramData response : responseList) {
			if (response instanceof Message) return ((Message) response);
		}
		return null;
	}
	
	
	/**
	 *Use this method to send general files. Bots can currently send files of any type of up to 50 MB in size, this limit may be changed in the future.
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param document File to send. You can either pass a file_id as String to resend a file that is already on the Telegram servers, or upload a new file using multipart/form-data.
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned,  on failure a null object is returned
	 */
	public Message sendDocument(int chatId, String document, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
		if(document == null || document.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId and a non-empty file id");
		//Building request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
		params.add(new BasicNameValuePair("document", document));
		if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
		if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
		//Making request
		List<TelegramData> responseList = outputParser.request(TelegramMethods.sendDocument, params);
		//Interpreting response
		for (TelegramData response : responseList) {
			if (response instanceof Message) return ((Message) response);
		}
		return null;
	}
	
	/**
	 * Use this method to send .webp stickers.
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param sticker Sticker to send. You can either pass a file_id as String to resend a sticker that is already on the Telegram servers, or upload a new sticker using multipart/form-data.
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned,  on failure a null object is returned
	 */
	public Message sendSticker(int chatId, String sticker, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
				if(sticker == null || sticker.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId and a non-empty sticker id");
				//Building request parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
				params.add(new BasicNameValuePair("sticker", sticker));
				if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
				if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
				//Making request
				List<TelegramData> responseList = outputParser.request(TelegramMethods.sendSticker, params);
				//Interpreting response
				for (TelegramData response : responseList) {
					if (response instanceof Message) return ((Message) response);
				}
				return null;
	}
	
	/**
	 * Use this method to send photos
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param video Video to send. You can either pass a file_id as String to resend a video that is already on the Telegram servers, or upload a new video file using multipart/form-data.
	 * @param duration (Optional) Duration of sent video in seconds
	 * @param caption (Optional) Video caption (may also be used when resending photos by file_id).
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned, on failure a null object is returned
	 */
	public Message sendVideo(int chatId, String photo, String video, int duration, String caption, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
				if(video == null || video.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId and a non-empty video id");
				//Building request parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
				params.add(new BasicNameValuePair("video", video));
				if(duration>0) params.add(new BasicNameValuePair("duration", Integer.toString(duration)));
				if(!(caption.equals("") || caption == null)) params.add(new BasicNameValuePair("caption", caption));
				if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
				if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
				//Making request
				List<TelegramData> responseList = outputParser.request(TelegramMethods.sendVideo, params);
				//Interpreting response
				for (TelegramData response : responseList) {
					if (response instanceof Message) return ((Message) response);
				}
				return null;
	}
	
	/**
	 * Use this method to send point on the map.
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param latitude Latitude of location
	 * @param longitude Longitude of location
	 * @param replyToMessageId (Optional) If the message is a reply, ID of the original message
	 * @param replyMarkup (Optional) Additional interface options. A JSON-serialized object for a custom reply keyboard, instructions to hide keyboard or to force a reply from the user.
	 * @return On success, the sent Message is returned, on failure a null object is returned
	 */
	public Message sendLocation(int chatId, Float latitude, Float longitude, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
		if(chatId==0) throw new IllegalArgumentException("You must specify at least a chatId");
		//Building request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
		params.add(new BasicNameValuePair("latitude", Float.toString(latitude)));
		params.add(new BasicNameValuePair("longitude", Float.toString(longitude)));
		if(replyToMessageId>0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
		if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
		//Making request
		List<TelegramData> responseList = outputParser.request(TelegramMethods.sendLocation, params);
		//Interpreting response
		for (TelegramData response : responseList) {
			if (response instanceof Message) return ((Message) response);
		}
		return null;
	}
	
	/**
	 * Use this method when you need to tell the user that something is happening on the bot's side. The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients clear its typing status).
	 * @param chatId Unique identifier for the message recipient — User or GroupChat id
	 * @param action Type of action to broadcast.
	 */
	public void sendChatAction(int chatId, ChatAction action){
		//Checking required parameters
				if(chatId==0 || action==null) throw new IllegalArgumentException("You must specify at least a chatId and a valid action");
				//Building request parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
				params.add(new BasicNameValuePair("action", action.getValue()));
				//Making request
				outputParser.request(TelegramMethods.sendChatAction, params);
	}
	
	/**
	 * Use this method to get a list of profile pictures for a user.
	 * @param userId Unique identifier of the target user
	 * @param offset (Optional) Sequential number of the first photo to be returned. By default, all photos are returned.
	 * @param limit (Optional) Limits the number of photos to be retrieved. Values between 1�100 are accepted. Defaults to 100.
	 * @return an {@link UserProfilePhotos} object.
	 */
	public UserProfilePhotos getUserProfilePhotos(int userId, int offset, int limit){
		//Checking required parameters
				if(userId<=0) throw new IllegalArgumentException("You must specify at least a chatId");
				//Building request parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("user_id", Integer.toString(userId)));
				if (offset > 0) params.add(new BasicNameValuePair("offset", Integer.toString(offset)));
				if (limit > 0) params.add(new BasicNameValuePair("limit", Integer.toString(limit)));

				//Making request
				List<TelegramData> responseList = outputParser.request(TelegramMethods.getUserProfilePhotos, params);
				//Interpreting response
				for (TelegramData response : responseList) {
					if (response instanceof UserProfilePhotos) return ((UserProfilePhotos) response);
				}
				return null;
	}

	public Message sendVoice(int chatId, String voice, int duration, boolean disableNotification, int replyToMessageId, CustomReplyKeyboard replyMarkup){
		//Checking required parameters
		if(voice == null || voice.equals("") || chatId==0) throw new IllegalArgumentException("You must specify at least a chatId and a non-empty voice id");
		//Building request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("chat_id", Integer.toString(chatId)));
		params.add(new BasicNameValuePair("voice", voice));


		if(duration>0) params.add(new BasicNameValuePair("duration", Integer.toString(duration)));
		if (disableNotification) params.add(new BasicNameValuePair("disable_notification", "true" ));
		if(replyToMessageId!=0)  params.add(new BasicNameValuePair("reply_to_message_id", Integer.toString(replyToMessageId)));
		if(replyMarkup!= null) params.add(new BasicNameValuePair("reply_markup", replyMarkup.toJSONObject().toString()));
		//Making request
		List<TelegramData> responseList = outputParser.request(TelegramMethods.sendVoice, params);
		//Interpreting response
		for (TelegramData response : responseList) {
			if (response instanceof Message) return ((Message) response);
		}
		return null;
	}

	public void answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, int cacheTime, boolean isPersonal, String nextOffset){
		//Checking required parameters
		if(inlineQueryId == null || inlineQueryId.equals("") || inlineQueryId == null ) throw new IllegalArgumentException("You must specify at least a queryId and a non-null result array");
		//Building request Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("inline_query_id", inlineQueryId));
        JSONArray jsonResults = new JSONArray();
        for (InlineQueryResult result: results) {
            jsonResults.put(result.toJSONObject());
        }
        params.add(new BasicNameValuePair("results", jsonResults.toString()));

        if (cacheTime>0) {
            params.add(new BasicNameValuePair("cache_time", Integer.toString(cacheTime)));
        }
        params.add(new BasicNameValuePair("is_personal", isPersonal?"true":"false"));
        if(nextOffset!=null){
            params.add(new BasicNameValuePair("next_offset", nextOffset));
        }

        //Making request
        outputParser.request(TelegramMethods.answerInlineQuery, params);
	}

	/**
	 * Call this method to get the link for downloading a file.
     */
	public File getFile(String fileId){
		//Checking required parameters
		if(fileId == null) throw new IllegalArgumentException("You must specify at least a file Id");
		//Building request parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("file_id", fileId));

		//Making request
		List<TelegramData> responseList = outputParser.request(TelegramMethods.getFile, params);
		//Interpreting response
		for (TelegramData response : responseList) {
			if (response instanceof File) return ((File) response);
		}
		return null;
	}

		
	public void startPolling(long millis){
		pollingExecutor = Executors.newSingleThreadScheduledExecutor();
		 scheduledPolling = pollingExecutor.scheduleWithFixedDelay(new PollingService(this), 0, millis, TimeUnit.MILLISECONDS);
		
	}

	public void stopPolling(){
		if (scheduledPolling!=null) scheduledPolling.cancel(false);
		if (pollingExecutor != null) pollingExecutor.shutdown();
		log("Polling stopped");

	}
	
	public void log(String message){
		Logger.getGlobal().log(Level.WARNING, message);
	}
	
	public TelegramEvents getHandler(){return eventHandler;}
	
	class PollingService implements Runnable {
		TelegramBot owner;
		public PollingService(TelegramBot owner) {
			this.owner = owner;
			
		}
		@Override
		public void run() {
			owner.getUpdates();
			if (owner.eventHandler == null){
				owner.log("Polling has started with no event handler associated!");
			}
		}
		}
	
	
}
