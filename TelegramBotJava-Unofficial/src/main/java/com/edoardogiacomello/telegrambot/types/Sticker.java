package com.edoardogiacomello.telegrambot.types;


public class Sticker implements TelegramData {
 private String fileId;
 private int width;
 private int height;
 private PhotoSize thumb;
 private int fileSize;
public Sticker(String fileId, int width, int height) {
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
public PhotoSize getThumb() {
	return thumb;
}
public int getFileSize() {
	return fileSize;
}
public void setThumb(PhotoSize thumb) {
	this.thumb = thumb;
}
public void setFileSize(int fileSize) {
	this.fileSize = fileSize;
}
 
}
