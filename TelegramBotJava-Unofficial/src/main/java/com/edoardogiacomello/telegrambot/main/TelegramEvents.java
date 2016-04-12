package com.edoardogiacomello.telegrambot.main;

import com.edoardogiacomello.telegrambot.types.*;
import com.edoardogiacomello.telegrambot.types.inline.InlineQuery;

import java.util.List;

public abstract class TelegramEvents {
	/**
	 * This event is fired whenever the bot receives an update from the server. Override this method if you want to have control over the update id received from the server
	 * @param update
	 */
	public void onUpdate(Update update){

	}

	/**
	 * This method analyzes the content of the received message and fires the appropriate method. Override this to gain a little performance speedup when treating a high number of messages
	 * @param message
     */
	void readMessage(Message message){
		onMessageReceived(message);
		if (message.getText() != null && !message.getText().equals("")){
			onTextMessageReceived(message,message.getText());
		}
		if(message.getPhoto() != null && !message.getPhoto().isEmpty()){
			onPhotoReceived(message,message.getPhoto(), message.getCaption());
		}
		if (message.getAudio() != null){
			onAudioReceived(message,message.getAudio());
		}
		if (message.getDocument() != null){
			onDocumentReceived(message, message.getDocument());
		}
		if (message.getSticker() != null){
			onStickerReceived(message,message.getSticker());
		}
		if (message.getVideo() != null){
			onVideoReceived(message,message.getVideo());
		}
		if (message.getVoice() != null){
			onVoiceReceived(message,message.getVoice());
		}
		if(message.getLocation() != null){
			onLocationReceived(message,message.getLocation());
		}
	}

	/**
	 * This event is fired whenever the bot receives a message from a user. Notice that the message could be text, photo, sticker etc.
	 * This method can be implemented if a functionality not covered by the other methods is needed and it's called before the specific events.
	 * @param message
	 */
	public abstract void onMessageReceived(Message message);

	/**
	 * The bot received a message that contains some text.
	 * @param message
     */
	public abstract void onTextMessageReceived(Message message, String text);

	public abstract void onPhotoReceived(Message message, List<PhotoSize> photos, String caption);

	public abstract void onAudioReceived(Message message, Audio audio);

	public abstract void onDocumentReceived(Message message, Document document);

	public abstract void onStickerReceived(Message message, Sticker sticker);

	public abstract void onVideoReceived(Message message, Video video);

	public abstract void onVoiceReceived(Message message, Voice voice);

	public abstract void onLocationReceived(Message message, Location location);

	public abstract void onInlineQueryReceived(InlineQuery inlineQuery);
}
