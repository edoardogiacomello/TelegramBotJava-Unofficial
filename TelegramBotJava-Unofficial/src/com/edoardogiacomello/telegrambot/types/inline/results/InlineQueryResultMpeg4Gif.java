package com.edoardogiacomello.telegrambot.types.inline.results;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default, this animated MPEG-4 file will be sent by the user with optional caption. Alternatively, you can provide message_text to send it instead of the animation.
 * Created by edoardo on 05/04/16.
 */
public class InlineQueryResultMpeg4Gif extends InlineQueryResult {
    private String mpeg4URL;
    private int mpeg4Width;
    private int mpeg4Height;
    private String thumbURL;
    private String title;
    private String caption;
    private String messageText;
    private String parseMode;
    private boolean disableWebPagePreview;

    public InlineQueryResultMpeg4Gif(String id, String mpeg4URL, String thumbURL) {
        super(id, InlineQueryResultType.MPEG4_GIF);
        this.mpeg4URL = mpeg4URL;
        this.thumbURL = thumbURL;
    }

    public String getMpeg4URL() {
        return mpeg4URL;
    }

    public int getMpeg4Width() {
        return mpeg4Width;
    }

    public int getMpeg4Height() {
        return mpeg4Height;
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

    public String getParseMode() {
        return parseMode;
    }

    public boolean isDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public void setMpeg4Width(int mpeg4Width) {
        this.mpeg4Width = mpeg4Width;
    }

    public void setMpeg4Height(int mpeg4Height) {
        this.mpeg4Height = mpeg4Height;
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

    public void setParseMode(String parseMode) {
        this.parseMode = parseMode;
    }

    public void setDisableWebPagePreview(boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
    }
}
