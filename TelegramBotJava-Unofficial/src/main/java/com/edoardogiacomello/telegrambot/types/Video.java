package com.edoardogiacomello.telegrambot.types;


public class Video implements TelegramData  {
	private String fileId;
	private int width;
	private int height;
	private int duration;
	private PhotoSize thumb;
	private String mimeType;
	private int fileSize;
	private String caption;
	public Video(String fileId, int width, int height, int duration) {
		super();
		this.fileId = fileId;
		this.width = width;
		this.height = height;
		this.duration = duration;
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
	public int getDuration() {
		return duration;
	}
	public PhotoSize getThumb() {
		return thumb;
	}
	public String getMimeType() {
		return mimeType;
	}
	public int getFileSize() {
		return fileSize;
	}
	public String getCaption() {
		return caption;
	}
	public void setThumb(PhotoSize thumb) {
		this.thumb = thumb;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
}
