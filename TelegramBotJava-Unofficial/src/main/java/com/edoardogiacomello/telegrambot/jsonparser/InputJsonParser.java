package com.edoardogiacomello.telegrambot.jsonparser;

import com.edoardogiacomello.telegrambot.types.*;
import com.edoardogiacomello.telegrambot.types.inline.InlineQuery;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputJsonParser {
 private InputJsonParser() {
 }

/**
 * Return a list containing the representation of a Telegram response, or null if the request failed
 * @param responseStream
 * @return
 */
public static List<TelegramData> parseResponse(InputStream responseStream){
	List<TelegramData> responseList = new ArrayList<TelegramData>();
	try{
	JSONObject jsonResponse = parseJSON(responseStream);
	/* Debug */
		//System.out.println("Received:" + jsonResponse.toString());
	//Checking if request has been satisfied
	if (jsonResponse.getBoolean("ok")){
		JSONArray jsonResultsArray = new JSONArray();
		Object jsonResultObject = jsonResponse.get("result");
		//The "result" field can be whether an array or an object
		if (jsonResultObject instanceof JSONArray) jsonResultsArray = jsonResponse.getJSONArray("result");
		else if (jsonResultObject instanceof JSONObject) jsonResultsArray.put(((JSONObject)jsonResultObject));
		//Response can be a Message, a User or a ProfilePhoto
		for (int i = 0; i < jsonResultsArray.length(); i++) {
			JSONObject jsonResult = jsonResultsArray.getJSONObject(i);
			//checking if the response is an update
			if (jsonResult.has("update_id")){
				if(jsonResult.has("message")) {
					responseList.add(new Update(jsonResult.getInt("update_id"), parseMessage(jsonResult.getJSONObject("message"))));
				} else if (jsonResult.has("inline_query")){
					responseList.add(new Update(jsonResult.getInt("update_id"), parseInlineQuery(jsonResult.getJSONObject("inline_query"))));
				} else responseList.add(new Update(jsonResult.getInt("update_id")));
			}
		//checking if the response is a User
			else if (jsonResult.has("id") && jsonResult.has("first_name")) {
				responseList.add(parseUser(jsonResult));
			}
			
			else if (jsonResult.has("message_id") && jsonResult.has("from") &&jsonResult.has("date") &&jsonResult.has("chat") )  {
				//In this case the response contains a message
				responseList.add(parseMessage(jsonResult));
			}
            else if (jsonResult.has("file_path")){
                //In this case, a file is returned from a getFile request
                responseList.add(parseFile(jsonResult));
            }
			
		}
		} else {
		Logger.getGlobal().log(Level.WARNING, "Telegram request failed: " + jsonResponse.toString());
		}
	}catch (Exception e){
		Logger.getGlobal().log(Level.WARNING, "Got bad response from the server, please check the authorization token or your internet connection");
		Logger.getGlobal().log(Level.SEVERE, e.toString());
	} finally
	{
		return responseList;
	}
}




    private static JSONObject parseJSON(InputStream responseStream) throws IOException{
		String jsonString = IOUtils.toString(responseStream,"UTF-8"); 
		return new JSONObject(jsonString);
		
	}
	private static InlineQuery parseInlineQuery(JSONObject jsonInlineQuery) {
		return new InlineQuery(jsonInlineQuery.getString("id"),parseUser(jsonInlineQuery.getJSONObject("from")),jsonInlineQuery.getString("query"),jsonInlineQuery.getString("offset"));
	}
	private static Message parseMessage(JSONObject jsonMessage){
		//TODO: Parse a message into an object
		Message responseMessage = new Message(jsonMessage.getInt("message_id"), parseUser(jsonMessage.getJSONObject("from")), jsonMessage.getInt("date"), parseChat(jsonMessage.getJSONObject("chat")));
		if(jsonMessage.has("forward_from")){responseMessage.setForwardFrom(parseUser(jsonMessage.getJSONObject("forward_from")));}
		if(jsonMessage.has("forward_date")){responseMessage.setForwardDate(jsonMessage.getInt("forward_date"));}
		if(jsonMessage.has("reply_to_message")){responseMessage.setReplyToMessage(parseMessage(jsonMessage.getJSONObject("reply_to_message")));}
		if(jsonMessage.has("text")){responseMessage.setText(jsonMessage.getString("text"));}
		if(jsonMessage.has("audio")){responseMessage.setAudio(parseAudio(jsonMessage.getJSONObject("audio")));}
		if(jsonMessage.has("document")){responseMessage.setDocument(parseDocument(jsonMessage.getJSONObject("document")));}
		if(jsonMessage.has("photo")){
			responseMessage.setPhoto(parsePhoto(jsonMessage.getJSONArray("photo")));}
		if(jsonMessage.has("sticker")){responseMessage.setSticker(parseSticker(jsonMessage.getJSONObject("sticker")));}
		if(jsonMessage.has("video")){responseMessage.setVideo(parseVideo(jsonMessage.getJSONObject("video")));}
		if(jsonMessage.has("contact")){responseMessage.setContact(parseContact(jsonMessage.getJSONObject("contact")));}
		if(jsonMessage.has("location")){responseMessage.setLocation(parseLocation(jsonMessage.getJSONObject("location")));}
		if(jsonMessage.has("new_chat_participant")){responseMessage.setNewChatParticipant(parseUser(jsonMessage.getJSONObject("new_chat_participant")));}
		if(jsonMessage.has("left_chat_participant")){responseMessage.setLeftChatParticipant(parseUser(jsonMessage.getJSONObject("left_chat_participant")));}
		if(jsonMessage.has("new_chat_title")){responseMessage.setNewChatTitle(jsonMessage.getString("new_chat_title"));}
		if(jsonMessage.has("new_chat_photo")){responseMessage.setNewChatPhoto(parsePhoto(jsonMessage.getJSONArray("new_chat_photo")));}
		if(jsonMessage.has("delete_chat_photo")){responseMessage.setDeleteChatPhoto(jsonMessage.getBoolean("delete_chat_photo"));}
		if(jsonMessage.has("group_chat_created")){responseMessage.setGroupChatCreated(jsonMessage.getBoolean("group_chat_created"));}
		if(jsonMessage.has("caption")){responseMessage.setCaption(jsonMessage.getString("caption"));}
        if(jsonMessage.has("voice")){responseMessage.setVoice(parseVoice(jsonMessage.getJSONObject("voice")));}
        if(jsonMessage.has("supergroup_chat_created")){responseMessage.setSupergroupChatCreated(jsonMessage.getBoolean("supergroup_chat_created"));}
        if(jsonMessage.has("channel_chat_created")){responseMessage.setChannelChatCreated(jsonMessage.getBoolean("channel_chat_created"));}
        if(jsonMessage.has("migrate_to_chat_id")){responseMessage.setMigrateToChatId(jsonMessage.getInt("migrate_to_chat_id"));}
        if(jsonMessage.has("migrate_from_chat_id")){responseMessage.setMigrateToChatId(jsonMessage.getInt("migrate_from_chat_id"));}

            return responseMessage;
	}
	
	private static Location parseLocation(JSONObject jsonLocation) {
		//TODO: Test this
		if (jsonLocation.has("longitude") && jsonLocation.has("latitude")) return new Location(Float.parseFloat(Double.toString(jsonLocation.getDouble("longitude"))), Float.parseFloat(Double.toString(jsonLocation.getDouble("latitude"))));
return null;
	}

	private static Contact parseContact(JSONObject jsonContact) {
		Contact responseContact = new Contact(jsonContact.getString("phone_number"), jsonContact.getString("first_name"));
		if (jsonContact.has("last_name")) responseContact.setLastName(jsonContact.getString("last_name"));
		if (jsonContact.has("user_id")) responseContact.setUserId(jsonContact.getInt("user_id"));
		return responseContact;
	}

	private static Video parseVideo(JSONObject jsonVideo) {
		Video responseVideo = new Video(jsonVideo.getString("file_id"), jsonVideo.getInt("width"), jsonVideo.getInt("height"), jsonVideo.getInt("duration"));
		if (jsonVideo.has("thumb")) responseVideo.setThumb(parsePhotoSize(jsonVideo.getJSONObject("thumb")));
		if (jsonVideo.has("mime_type")) responseVideo.setMimeType(jsonVideo.getString("mime_type"));
		if (jsonVideo.has("file_size")) responseVideo.setFileSize(jsonVideo.getInt("file_size"));
		return responseVideo;
	}

	private static Sticker parseSticker(JSONObject jsonSticker) {
		Sticker responseSticker = new Sticker(jsonSticker.getString("file_id"), jsonSticker.getInt("width"), jsonSticker.getInt("height"));
		if (jsonSticker.has("thumb")) responseSticker.setThumb(parsePhotoSize(jsonSticker.getJSONObject("thumb")));
		if (jsonSticker.has("file_size")) responseSticker.setFileSize(jsonSticker.getInt("file_size"));
		return responseSticker;
	}

	/**
	 * Parse an array of PhotoSize, containing all available sizes of a sent picture
	 * @param jsonPhotoArray
	 * @return
	 */
	private static List<PhotoSize> parsePhoto(JSONArray jsonPhotoArray) {
		List<PhotoSize> responsePhotoSizeList = new ArrayList<PhotoSize>();
		for (int i = 0; i < jsonPhotoArray.length(); i++) {
			responsePhotoSizeList.add(parsePhotoSize(jsonPhotoArray.getJSONObject(i)));
		}
		return responsePhotoSizeList;
	}
	/**
	 * Parse a PhotoSize object, usually a thumb for a document or a sticker
	 * @param jsonPhotoSize
	 * @return
	 */
	private static PhotoSize parsePhotoSize(JSONObject jsonPhotoSize){
		PhotoSize responsePhotoSize = new PhotoSize(jsonPhotoSize.getString("file_id"), jsonPhotoSize.getInt("width"), jsonPhotoSize.getInt("height"));
		if(jsonPhotoSize.has("file_size")) responsePhotoSize.setFileSize(jsonPhotoSize.getInt("file_size"));
		return responsePhotoSize;
	}

	private static Document parseDocument(JSONObject jsonDocument) {
		Document responseDocument = new Document(jsonDocument.getString("file_id"));
		if (jsonDocument.has("thumb")) responseDocument.setThumb(parsePhotoSize(jsonDocument.getJSONObject("thumb")));
		if (jsonDocument.has("file_name")) responseDocument.setFileName(jsonDocument.getString("file_name"));
		if (jsonDocument.has("mime_type")) responseDocument.setMimeType(jsonDocument.getString("mime_type"));
		if (jsonDocument.has("file_size")) responseDocument.setFileSize(jsonDocument.getInt("file_size"));
		return responseDocument;
	}

	private static User parseUser(JSONObject jsonUser){
		User responseUser = new User(jsonUser.getInt("id"), jsonUser.getString("first_name"));
		if (jsonUser.has("last_name"))responseUser.setLastName(jsonUser.getString("last_name"));
		if (jsonUser.has("username"))responseUser.setUserName(jsonUser.getString("username"));
		return responseUser;
	}
	
	/**
	 * Parses the "chat" field of a telegram Message
	 * @param jsonChat
	 * @return
	 */
	private static Chat parseChat(JSONObject jsonChat){
		Chat chat = new Chat(jsonChat.getInt("id"),ChatType.getType(jsonChat.getString("type")));
        if (jsonChat.has("title")){chat.setTitle(jsonChat.getString("title"));}
        if (jsonChat.has("username")){chat.setUsername(jsonChat.getString("username"));}
        if (jsonChat.has("first_name")){chat.setFirstName(jsonChat.getString("first_name"));}
        if (jsonChat.has("last_name")){chat.setLastName(jsonChat.getString("last_name"));}
       return chat;
	}
	private static Audio parseAudio(JSONObject jsonAudio){
		Audio responseAudio = new Audio(jsonAudio.getString("file_id"), jsonAudio.getInt("duration"));
		if(jsonAudio.has("mime_type")) responseAudio.setMimeType(jsonAudio.getString("mime_type"));
		if(jsonAudio.has("file_Size")) responseAudio.setFileSize(jsonAudio.getInt("file_size"));
		return responseAudio;
	}

    private static Voice parseVoice(JSONObject jsonVoice){
        Voice responseVoice = new Voice(jsonVoice.getString("file_id"),jsonVoice.getInt("duration"));
        if(jsonVoice.has("mime_type")) {responseVoice.setMimeType(jsonVoice.getString("mime_type"));}
        if(jsonVoice.has("file_size")) {responseVoice.setFileSize(jsonVoice.getInt("file_size"));}
        return responseVoice;
    }

    private static TelegramFile parseFile(JSONObject jsonFile) {
            TelegramFile telegramFile = new TelegramFile(jsonFile.getString("file_id"));
            if (jsonFile.has("file_path")) telegramFile.setFilePath(jsonFile.getString("file_path"));
            if (jsonFile.has("file_size")) telegramFile.setFileSize(jsonFile.getInt("file_size"));
        return telegramFile;
    }
}
