package com.edoardogiacomello.telegrambot.types.inline.results;

/**This object represents one result of an inline query.
 * Created by edoardo on 05/04/16.
 */
public abstract class InlineQueryResult {
    String id;
    InlineQueryResultType type;

    public InlineQueryResult(String id, InlineQueryResultType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

}

