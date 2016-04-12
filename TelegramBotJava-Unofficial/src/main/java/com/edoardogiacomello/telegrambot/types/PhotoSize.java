package com.edoardogiacomello.telegrambot.types;


public class PhotoSize implements TelegramData {
 private String fileId;
 private int width;
 private int height;
 private int fileSize;
public PhotoSize(String fileId, int width, int height) {
	super();
	this.fileId = fileId;
	this.width = width;
	this.height = height;
}
public String getFileId() {
	return fileId;
}
public int getWidth() {
	return width;
}
public int getHeight() {
	return height;
}
public int getFileSize() {
	return fileSize;
}
public void setFileSize(int fileSize) {
	this.fileSize = fileSize;
}
 
}
