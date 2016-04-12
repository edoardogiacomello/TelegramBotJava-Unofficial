package com.edoardogiacomello.telegrambot.types;

@Deprecated
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


	@Override
	public String toString(){
		StringBuffer string = new StringBuffer();
		string.append("groupChatId: " + groupChatId + ", ");
		if (title != null)
			string.append("title: " +title);
		return string.toString();
	}
}
