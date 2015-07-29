package com.edoardogiacomello.telegrambot.types;


public class GroupChat implements TelegramData {
	private int groupChatId;
	private String title;
	public GroupChat(int groupChatId, String title) {
		super();
		this.groupChatId = groupChatId;
		this.title = title;
	}
	public int getGroupChatId() {
		return groupChatId;
	}
	public String getTitle() {
		return title;
	}
	
}
