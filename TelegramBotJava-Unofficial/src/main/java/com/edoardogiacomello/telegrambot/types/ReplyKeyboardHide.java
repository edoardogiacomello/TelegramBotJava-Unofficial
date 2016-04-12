package com.edoardogiacomello.telegrambot.types;

import org.json.JSONObject;

public class ReplyKeyboardHide extends CustomReplyKeyboard {
	private boolean hideKeyboard;
	private boolean selective;
	public ReplyKeyboardHide(boolean selective) {
		super();
		hideKeyboard=true;
		this.selective = selective;
	}
	public boolean isHideKeyboard() {
		return hideKeyboard;
	}
	public boolean isSelective() {
		return selective;
	}
	@Override
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("hide_keyboard", hideKeyboard);
		json.put("selective", selective);
		return json;
	}
	
}
