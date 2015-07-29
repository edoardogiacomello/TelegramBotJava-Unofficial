package com.edoardogiacomello.telegrambot.types;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReplyKeyboardMarkup extends CustomReplyKeyboard {
	private List<List<String>> keyboard;
	private boolean resizeKeyboard;
	private boolean onetimeKeyboard;
	private boolean selective;
	public ReplyKeyboardMarkup(List<List<String>> keyboard,
			boolean resizeKeyboard, boolean onetimeKeyboard, boolean selective) {
		super();
		this.keyboard = keyboard;
		this.resizeKeyboard = resizeKeyboard;
		this.onetimeKeyboard = onetimeKeyboard;
		this.selective = selective;
	}
	public List<List<String>> getKeyboard() {
		return keyboard;
	}
	public boolean isResizeKeyboard() {
		return resizeKeyboard;
	}
	public boolean isOnetimeKeyboard() {
		return onetimeKeyboard;
	}
	public boolean isSelective() {
		return selective;
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		JSONArray jsonKeyboard = new JSONArray();
		for (List<String> currentRow : keyboard) {
			JSONArray buttonRow = new JSONArray();
			for (String button : currentRow) {
				buttonRow.put(button);
			}
			jsonKeyboard.put(buttonRow);
		}
		json.put("keyboard", jsonKeyboard);
		json.put("resize_keyboard", resizeKeyboard);
		json.put("one_time_keyboard", onetimeKeyboard);
		json.put("selective", selective);
		return json;
	}
	
	
}
