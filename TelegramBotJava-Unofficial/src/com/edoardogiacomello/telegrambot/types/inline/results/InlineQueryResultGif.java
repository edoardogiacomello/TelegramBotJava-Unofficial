package com.edoardogiacomello.telegrambot.types.inline.results;

import com.edoardogiacomello.telegrambot.methods.TelegramMethods;

/**
 * Represents a link to an animated GIF file. By default, this animated GIF file will be sent by the user with optional caption. Alternatively, you can provide message_text to send it instead of the animation.
 * Created by edoardo on 05/04/16.
 */
public class InlineQueryResultGif extends InlineQueryResult{
    private String gifURL;
    private int gifWidth;
    private int gifHeight;
    private String thumbURL;
    private String title;
    private String caption;
    private String messageText;
    private TelegramMethods.ParseMode parseMode;
    private boolean disableWebPagePreview;

    public InlineQueryResultGif(String id, String gifURL, String thumbURL) {
        super(id, InlineQueryResultType.GIF);
        this.gifURL = gifURL;
        this.thumbURL = thumbURL;
    }

    public String getGifURL() {
        return gifURL;
    }

    public int getGifWidth() {
        return gifWidth;
    }

    public int getGifHeight() {
        return gifHeight;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getMessageText() {
        return messageText;
    }

    public TelegramMethods.ParseMode getParseMode() {
        return parseMode;
    }

    public boolean isDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public void setGifWidth(int gifWidth) {
        this.gifWidth = gifWidth;
    }

    public void setGifHeight(int gifHeight) {
        this.gifHeight = gifHeight;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setParseMode(TelegramMethods.ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    public void setDisableWebPagePreview(boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
    }
}
