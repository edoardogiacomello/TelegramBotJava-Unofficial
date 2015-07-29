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
	private Sticker Sticker;
	private Video video;
	private Contact contact;
	private Location location;
	private User newChatParticipant;
	private User leftChatParticipant;
	private String NewChatTitle;
	private List<PhotoSize> newChatPhoto;
	private boolean deleteChatPhoto;
	private boolean groupChatCreated;
	private String caption;
	
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
		Sticker = sticker;
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
		NewChatTitle = newChatTitle;
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
		return Sticker;
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
		return NewChatTitle;
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


	public Message(int messageId, User from, int date, Chat chat) {
		super();
		this.messageId = messageId;
		this.from = from;
		this.date = date;
		this.chat = chat;
	}
	
}
