package com.edoardogiacomello.telegrambot.types.inline;

import com.edoardogiacomello.telegrambot.types.User;

/**
 * This object represents an incoming inline query. When the user sends an empty query, your bot could return some default or trending results.
 * Created by Edoardo Giacomello <edoardo.giacomello1990@gmail.com> on 05/04/16.
 */
public class InlineQuery {

    private String id;
    private User from;
    private String query;
    private String offset;

    public InlineQuery(String id, User from, String query, String offset) {
        this.id = id;
        this.from = from;
        this.query = query;
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public String getQuery() {
        return query;
    }

    public String getOffset() {
        return offset;
    }
}
