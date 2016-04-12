package com.edoardogiacomello.telegrambot.types;

import java.util.List;

public class UserProfilePhotos implements TelegramData{
	private int totalCount;
	private List<List<PhotoSize>> photos;
	public UserProfilePhotos(List<List<PhotoSize>> photos) {
		super();
		setPhotos(photos);
	}
	public List<List<PhotoSize>> getPhotos() {
		return photos;
	}
	public void setPhotos(List<List<PhotoSize>> photos) {
		this.photos = photos;
		totalCount = photos.size();
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	
}
