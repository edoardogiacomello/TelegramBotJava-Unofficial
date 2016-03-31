package com.edoardogiacomello.telegrambot.methods;

public enum TelegramMethods {
	getMe("getMe"),
	sendMessage("sendMessage"),
	forwardMessage("forwardMessage"),
	sendPhoto("sendPhoto"),
	sendAudio("sendAudio"),
	sendDocument("sendDocument"),
	sendSticker("sendSticker"),
	sendVideo("sendVideo"),
	sendLocation("sendLocation"),
	sendChatAction("sendChatAction"),
	getUserProfilePhotos("getUserProfilePhotos"),
	getUpdates("getUpdates"), 
	setWebhook("setWebhook"),
	sendVoice("sendVoice");
	private String value;
	private TelegramMethods(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
