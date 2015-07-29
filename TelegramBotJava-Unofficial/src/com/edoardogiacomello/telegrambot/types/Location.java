package com.edoardogiacomello.telegrambot.types;


public class Location implements TelegramData {
	private float longitude;
	private float latitude;
	public Location(float longitude, float latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	
}
