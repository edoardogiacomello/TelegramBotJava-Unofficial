package com.edoardogiacomello.telegrambot.types.inline;

import com.edoardogiacomello.telegrambot.types.User;

/**
 * This object represents a result of an inline query that was chosen by the user and sent to their chat partner.
 */
public class ChosenInlineResult {
    private String resultId;
    private User from;
    private String query;

    public ChosenInlineResult(String resultId, User from, String query) {
        this.resultId = resultId;
        this.from = from;
        this.query = query;
    }

    public String getResultId() {
        return resultId;
    }

    public User getFrom() {
        return from;
    }

    public String getQuery() {
        return query;
    }
}
