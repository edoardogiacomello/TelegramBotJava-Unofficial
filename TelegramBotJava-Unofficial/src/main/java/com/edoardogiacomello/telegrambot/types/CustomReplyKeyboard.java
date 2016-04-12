package com.edoardogiacomello.telegrambot.types;

import org.json.JSONObject;

public abstract class CustomReplyKeyboard implements TelegramData {
 public abstract JSONObject toJSONObject();
}
