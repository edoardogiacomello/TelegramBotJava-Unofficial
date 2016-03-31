package com.edoardogiacomello.telegrambot.types;

/**
 * This object represents a voice note
 * Created by Edoardo Giacomello <edoardo.giacomello1990@gmail.com> on 31/03/16.
 */
public class Voice implements TelegramData{
    private String fileId;
    private Integer duration;
    private String mimeType; //Optional
    private Integer fileSize; //Optional

    public Voice(String fileId, Integer duration) {
        this.fileId = fileId;
        this.duration = duration;
    }

    public Voice(String fileId, Integer duration, String mimeType, Integer fileSize) {
        this.fileId = fileId;
        this.duration = duration;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
    }

    public String getFileId() {
        return fileId;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Integer getFileSize() {
        return fileSize;
    }
}
