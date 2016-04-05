package com.edoardogiacomello.telegrambot.types;


public class Chat implements TelegramData  {
	private int chatId; //Unique identifier for this chat, not exceeding 1e13 by absolute value
	private ChatType type; //Type of chat, can be either “private”, “group”, “supergroup” or “channel”
	private String title; //Optional. Title, for channels and group chats
	private String username; //Optional. Username, for private chats and channels if available
	private String firstName; //Optional. First name of the other party in a private chat
	private String lastName; //Optional. Last name of the other party in a private chat

    public Chat(int chatId, ChatType type) {
        this.chatId = chatId;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getChatId() {
        return chatId;
    }

    public ChatType getType() {
        return type;
    }

    @Override
	public String toString(){
		StringBuffer string = new StringBuffer();
        string.append("chatId: " + chatId + ", ");
		if (type != null)
		string.append("type: " + type.toString() + ", ");
        if (title != null)
            string.append("title: " + title + ", ");
        if (username != null)
            string.append("username: " + username + ", ");
        if (firstName != null)
            string.append("firstName: " + firstName + ", ");
        if (lastName != null)
            string.append("lastName: " + lastName + ", ");
		return string.toString();
	}
	
	
}
