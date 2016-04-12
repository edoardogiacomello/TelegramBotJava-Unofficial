package com.edoardogiacomello.telegrambot.types;

public enum ChatAction {
	TYPING("typing"),
	UPLOAD_PHOTO("upload_photo"),
	RECORD_VIDEO("record_video"),
	UPLOAD_VIDEO("upload_video"),
	UPLOAD_AUDIO("upload_audio"), 
	RECORD_AUDIO("record_audio"), 
	UPLOAD_DOCUMENT("upload_document"), 
	FIND_LOCATION("find_location");
	String value;
	private ChatAction(String value) {
	this.value = value;
	}
	public String getValue() {
		return value;
	}
}
