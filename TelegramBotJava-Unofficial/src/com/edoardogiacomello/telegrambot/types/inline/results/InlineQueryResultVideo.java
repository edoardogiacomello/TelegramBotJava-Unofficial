package com.edoardogiacomello.telegrambot.types.inline.results;

import com.edoardogiacomello.telegrambot.methods.TelegramMethods;

/**
 * Represents link to a page containing an embedded video player or a video file.
 * Created by edoardo on 05/04/16.
 */
public class InlineQueryResultVideo extends InlineQueryResult{
    private String videoURL;
    private String mimeType;
    private String messageText;
    private String thumbUrl;
    private String title;
    private TelegramMethods.ParseMode parseMode;
    private boolean disableWebPagePreview;
    private int videoWidth;
    private int videoHeight;
    private int videoDuration;
    private String description;

    public InlineQueryResultVideo(String id, String videoURL, String mimeType, String messageText, String thumbUrl, String title) {
        super(id, InlineQueryResultType.VIDEO);
        this.videoURL = videoURL;
        this.mimeType = mimeType;
        this.messageText = messageText;
        this.thumbUrl = thumbUrl;
        this.title = title;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public TelegramMethods.ParseMode getParseMode() {
        return parseMode;
    }

    public boolean isDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParseMode(TelegramMethods.ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    public void setDisableWebPagePreview(boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
