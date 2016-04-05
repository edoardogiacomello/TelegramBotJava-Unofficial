package com.edoardogiacomello.telegrambot.types.inline.results;

/**
 * Created by edoardo on 05/04/16.
 */
public enum InlineQueryResultType {
    ARTICLE("article"), PHOTO("photo"), GIF("gif"), MPEG4_GIF("mpeg4_gif"), VIDEO("video");
    String value;

    InlineQueryResultType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
