package com.edoardogiacomello.telegrambot.types;

import java.util.List;

/**
 * This object represents a message.
 * @author Edoardo Giacomello <edoardo.giacomello1990@gmail.com>
 * @see https://core.telegram.org/bots/api#message
 */
public class Message implements TelegramData {
	private int messageId;
	private User from;
	private int date;
	private Chat chat;
	private User forwardFrom;
	private int forwardDate;
	private Message replyToMessage;
	private String text;
	private Audio audio;
	private Document document;
	private List<PhotoSize> photo;
	private Sticker sticker;
	private Video video;
	private Voice voice;
	private String caption;
	private Contact contact;
	private Location location;
	private User newChatParticipant;
	private User leftChatParticipant;
	private String newChatTitle;
	private List<PhotoSize> newChatPhoto;
	private boolean deleteChatPhoto;
	private boolean groupChatCreated;
	private boolean supergroupChatCreated;
	private boolean channelChatCreated;
	private int migrateToChatId;
	private int migrateFromChatId;

	
	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public void setForwardFrom(User forwardFrom) {
		this.forwardFrom = forwardFrom;
	}


	public void setForwardDate(int forwardDate) {
		this.forwardDate = forwardDate;
	}


	public void setReplyToMessage(Message replyToMessage) {
		this.replyToMessage = replyToMessage;
	}


	public void setText(String text) {
		this.text = text;
	}


	public void setAudio(Audio audio) {
		this.audio = audio;
	}


	public void setDocument(Document document) {
		this.document = document;
	}


	public void setPhoto(List<PhotoSize> photo) {
		this.photo = photo;
	}


	public void setSticker(Sticker sticker) {
		this.sticker = sticker;
	}


	public void setVideo(Video video) {
		this.video = video;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public void setNewChatParticipant(User newChatParticipant) {
		this.newChatParticipant = newChatParticipant;
	}


	public void setLeftChatParticipant(User leftChatParticipant) {
		this.leftChatParticipant = leftChatParticipant;
	}


	public void setNewChatTitle(String newChatTitle) {
		this.newChatTitle = newChatTitle;
	}


	public void setNewChatPhoto(List<PhotoSize> newChatPhoto) {
		this.newChatPhoto = newChatPhoto;
	}


	public void setDeleteChatPhoto(boolean deleteChatPhoto) {
		this.deleteChatPhoto = deleteChatPhoto;
	}


	public void setGroupChatCreated(boolean groupChatCreated) {
		this.groupChatCreated = groupChatCreated;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public void setSupergroupChatCreated(boolean supergroupChatCreated) {
		this.supergroupChatCreated = supergroupChatCreated;
	}

	public void setChannelChatCreated(boolean channelChatCreated) {
		this.channelChatCreated = channelChatCreated;
	}

	public void setMigrateToChatId(int migrateToChatId) {
		this.migrateToChatId = migrateToChatId;
	}

	public void setMigrateFromChatId(int migrateFromChatId) {
		this.migrateFromChatId = migrateFromChatId;
	}

	public int getMessageId() {
		return messageId;
	}


	public User getFrom() {
		return from;
	}


	public int getDate() {
		return date;
	}


	public Chat getChat() {
		return chat;
	}


	public User getForwardFrom() {
		return forwardFrom;
	}


	public int getForwardDate() {
		return forwardDate;
	}


	public Message getReplyToMessage() {
		return replyToMessage;
	}


	public String getText() {
		return text;
	}


	public Audio getAudio() {
		return audio;
	}


	public Document getDocument() {
		return document;
	}


	public List<PhotoSize> getPhoto() {
		return photo;
	}


	public Sticker getSticker() {
		return sticker;
	}


	public Video getVideo() {
		return video;
	}


	public Contact getContact() {
		return contact;
	}


	public Location getLocation() {
		return location;
	}


	public User getNewChatParticipant() {
		return newChatParticipant;
	}


	public User getLeftChatParticipant() {
		return leftChatParticipant;
	}


	public String getNewChatTitle() {
		return newChatTitle;
	}


	public List<PhotoSize> getNewChatPhoto() {
		return newChatPhoto;
	}


	public boolean isDeleteChatPhoto() {
		return deleteChatPhoto;
	}


	public boolean isGroupChatCreated() {
		return groupChatCreated;
	}

	public Voice getVoice() {
		return voice;
	}

	public int getMigrateFromChatId() {
		return migrateFromChatId;
	}

	public int getMigrateToChatId() {
		return migrateToChatId;
	}

	public boolean isSupergroupChatCreated() {
		return supergroupChatCreated;
	}

	public boolean isChannelChatCreated() {
		return channelChatCreated;
	}

	public Message(int messageId, User from, int date, Chat chat) {
		super();
		this.messageId = messageId;
		this.from = from;
		this.date = date;
		this.chat = chat;
	}

	/**
	 * Textual representation of all the information stored in a message
	 * @return The String representing the message
     */
	public String toString(){
		StringBuffer string = new StringBuffer();
		string.append("messageId = " + messageId +"\n");
		if(from!=null)
		string.append("from = " + from.toString() +"\n");
		string.append("date = " + date +"\n");
		if(chat!=null)
		string.append("chat = " + chat +"\n");
		if(forwardFrom!=null)
		string.append("forwardFrom = " + forwardFrom.toString() +"\n");
		string.append("forwardDate = " + forwardDate +"\n");
		if(replyToMessage!=null)
		string.append("replyToMessage (messageId) = " + replyToMessage.getMessageId() +"\n");
		string.append("text = " + text +"\n");
		if(audio!=null)
		string.append("audio = " + audio.toString() +"\n");
		if(document!=null)
		string.append("document = " + document.toString() +"\n");
		if(photo!=null)
		string.append("photo = " + photo.toString() +"\n");
		if(sticker !=null)
		string.append("sticker = " + sticker.toString() +"\n");
		if(video!=null)
		string.append("video = " + video.toString() +"\n");
		if(voice!=null)
		string.append("voice = " + voice.toString() +"\n");
		if(caption!=null)
		string.append("caption = " + caption +"\n");
		if(contact!=null)
		string.append("contact = " + contact.toString() +"\n");
		if(location!=null)
			string.append("location = " + location.toString() +"\n");
		if(newChatParticipant!=null)
			string.append("newChatParticipant = " + newChatParticipant.toString() +"\n");
		if(leftChatParticipant!=null)
		string.append("leftChatParticipant = " + leftChatParticipant.toString() +"\n");
		if(newChatTitle!=null)
		string.append("newChatTitle = " + newChatTitle +"\n");
		if(newChatPhoto!=null)
		string.append("newChatPhoto = " + newChatPhoto.toString() +"\n");
		string.append("groupChatCreated = " + groupChatCreated +"\n");
		string.append("deleteChatPhoto = " + deleteChatPhoto +"\n");
		string.append("supergroupChatCreated = " + supergroupChatCreated +"\n");
		string.append("channelChatCreated = " + channelChatCreated +"\n");
		string.append("migrateToChatId = " + migrateToChatId +"\n");
		string.append("migrateFromChatId = " + migrateFromChatId +"\n");


		return string.toString();
	}
	
}
