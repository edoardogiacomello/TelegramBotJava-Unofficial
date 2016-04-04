package com.edoardogiacomello.telegrambot.types;


public class Chat implements TelegramData  {
	private boolean isPrivateChat = true;
	private User user;
	private GroupChat groupChat;
	public Chat(GroupChat groupChat) {
		super();
		this.isPrivateChat = false;
		this.groupChat = groupChat;
	}
	public Chat(User user) {
		super();
		this.isPrivateChat= true;
		this.user = user;
	}
	public boolean isPrivateChat() {
		return isPrivateChat;
	}
	public User getUser() {
		return user;
	}
	public GroupChat getGroupChat() {
		return groupChat;
	}
	public int getChatId(){
		if (groupChat != null) return groupChat.getGroupChatId();
		if(user != null) return user.getUserId();
		else throw new IllegalStateException("This chat object has not been initialized");
	}

	@Override
	public String toString(){
		StringBuffer string = new StringBuffer();
		if (user != null)
		string.append("userId: " + user.toString() + ", ");
		if (groupChat != null)
		string.append("GroupChat: " + groupChat.toString());
		return string.toString();
	}
	
	
}
