package com.edoardogiacomello.telegrambot.types;



public class User implements TelegramData {
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	public User(int id, String firstName) {
		super();
		this.userId = id;
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	@Override
	public String toString(){
		StringBuffer string = new StringBuffer();
		string.append("id: " + userId + ", ");
		string.append("Name: " + firstName + " " + lastName + "(" + userName + ")");


		return string.toString();
	}

}
