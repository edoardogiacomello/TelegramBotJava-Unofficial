package com.edoardogiacomello.telegrambot.types;


public class Contact implements TelegramData {
private String phoneNumber;
private String firstName;
private String lastName;
private String userId;
public Contact(String phoneNumber, String firstName) {
	super();
	this.phoneNumber = phoneNumber;
	this.firstName = firstName;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
public String getUserId() {
	return userId;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public void setUserId(String userId) {
	this.userId = userId;
}

}
