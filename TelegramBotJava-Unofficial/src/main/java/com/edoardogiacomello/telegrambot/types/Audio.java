package com.edoardogiacomello.telegrambot.types;


public class Audio implements TelegramData {
private String fileId;
private int duration;
private String mimeType;
private int fileSize;
public Audio(String fileId, int duration){
	super();
	this.fileId = fileId;
	this.duration = duration;
}
public String getFileId() {
	return fileId;
}
public int getDuration() {
	return duration;
}
public String getMimeType() {
	return mimeType;
}
public int getFileSize() {
	return fileSize;
}
public void setMimeType(String mimeType) {
	this.mimeType = mimeType;
}
public void setFileSize(int fileSize) {
	this.fileSize = fileSize;
}


}
