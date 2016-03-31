package com.edoardogiacomello.telegrambot.types;


public class Contact implements TelegramData {
private String phoneNumber;
private String firstName;
private String lastName;
private Integer userId;
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
public Integer getUserId() {
	return userId;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}

}
