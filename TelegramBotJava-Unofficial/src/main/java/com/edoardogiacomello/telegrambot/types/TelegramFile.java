package com.edoardogiacomello.telegrambot.types;

/**
 * This object represents a file ready to be downloaded. The file can be downloaded via the link https://api.telegram.org/file/bot<token>/<file_path>. It is guaranteed that the link will be valid for at least 1 hour. When the link expires, a new one can be requested by calling getFile.
 * Created by Edoardo Giacomello on 12/04/16.
 */
public class TelegramFile implements TelegramData {
    private String fileId;
    private Integer fileSize;
    private String filePath;


    public TelegramFile(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
