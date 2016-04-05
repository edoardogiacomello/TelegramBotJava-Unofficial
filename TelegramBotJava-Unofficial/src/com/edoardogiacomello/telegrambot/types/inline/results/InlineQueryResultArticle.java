package com.edoardogiacomello.telegrambot.types.inline.results;

import com.edoardogiacomello.telegrambot.methods.TelegramMethods;


/**
 * Represents a link to an article or web page.
 * Created by edoardo on 05/04/16.
 */
public class InlineQueryResultArticle extends InlineQueryResult{
    private String title; //Title of the result
    private String messageText; //Text of the message to be sent, 1-4096 characters
    private TelegramMethods.ParseMode parseMode; //Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
    private boolean disableWebPagePreview; //Optional. Disables link previews for links in the sent message
    private String url; //Optional. URL of the result
    private boolean hideUrl; //Optional. Pass True, if you don't want the URL to be shown in the message
    private String description; //Optional. Short description of the result
    private String thumbUrl; //Optional. Url of the thumbnail for the result
    private int thumbWidth; //Optional. Thumbnail width
    private int thumbHeight; //Optional. Thumbnail height

    public InlineQueryResultArticle(String id, String title, String messageText) {
        super(id, InlineQueryResultType.ARTICLE);
        this.title = title;
        this.messageText = messageText;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHideUrl(boolean hideUrl) {
        this.hideUrl = hideUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public void setThumbWidth(int thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public void setThumbHeight(int thumbHeight) {
        this.thumbHeight = thumbHeight;
    }

    public String getTitle() {
        return title;
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

    public String getUrl() {
        return url;
    }

    public boolean isHideUrl() {
        return hideUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public int getThumbWidth() {
        return thumbWidth;
    }

    public int getThumbHeight() {
        return thumbHeight;
    }
}
