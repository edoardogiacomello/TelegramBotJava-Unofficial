package com.edoardogiacomello.telegrambot.types;


import com.edoardogiacomello.telegrambot.types.inline.InlineQuery;

/**
 * This object represents an incoming update.
 * @author Edoardo Giacomello <edoardo.giacomello1990@gmail.com>
 * @see https://core.telegram.org/bots/api#available-types
 */
public class Update implements TelegramData {
	private int update_id;
	private Message message;
	private InlineQuery inlineQuery;

	public Update(int update_id) {
		super();
		this.update_id = update_id;
	}
	public int getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(int update_id) {
		this.update_id = update_id;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Update(int update_id, Message message) {
		super();
		this.update_id = update_id;
		this.message = message;
	}

	public Update(int update_id, InlineQuery inlineQuery) {
		this.update_id = update_id;
		this.inlineQuery = inlineQuery;
	}

	public InlineQuery getInlineQuery() {
		return inlineQuery;
	}
}

