package com.edoardogiacomello.telegrambot.types;


public class Document implements TelegramData {
private String fileId;
private PhotoSize thumb;
private String fileName;
private String mimeType;
private int fileSize;
public Document(String fileId) {
	super();
	this.fileId = fileId;
}
public String getFileId() {
	return fileId;
}
public PhotoSize getThumb() {
	return thumb;
}
public String getFileName() {
	return fileName;
}
public String getMimeType() {
	return mimeType;
}
public int getFileSize() {
	return fileSize;
}
public void setThumb(PhotoSize thumb) {
	this.thumb = thumb;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public void setMimeType(String mimeType) {
	this.mimeType = mimeType;
}
public void setFileSize(int fileSize) {
	this.fileSize = fileSize;
}


}
