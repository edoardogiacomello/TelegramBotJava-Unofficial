package com.edoardogiacomello.telegrambot.main;

import com.edoardogiacomello.telegrambot.types.Message;
import com.edoardogiacomello.telegrambot.types.Update;

public abstract class TelegramEvents {
	/**
	 * This event is fired whenever the bot receives an update from the server. Use this method if you want to have control over the update id received from the server
	 * @param update
	 */
	public abstract void onUpdate(Update update);
	/**
	 * This event is fired whenever the bot receives a message from a user
	 * @param message
	 */
	public abstract void onMessageReceived(Message message);
}
