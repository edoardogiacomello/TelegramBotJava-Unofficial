package com.edoardogiacomello.telegrambot.types;

import org.json.JSONObject;

public class ForceReply extends CustomReplyKeyboard {
	private boolean forceReply;
	private boolean selective;
	public ForceReply(boolean selective) {
		super();
		this.forceReply = true;
		this.selective = selective;
	}
	
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("forceReply", forceReply);
		json.put("selective", selective);
		return json;
	}
	
}
