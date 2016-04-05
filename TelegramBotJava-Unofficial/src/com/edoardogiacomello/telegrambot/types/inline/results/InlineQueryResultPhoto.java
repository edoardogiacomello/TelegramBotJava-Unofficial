package com.edoardogiacomello.telegrambot.types.inline.results;

import com.edoardogiacomello.telegrambot.methods.TelegramMethods;

/**Represents a link to a photo. By default, this photo will be sent by the user with optional caption. Alternatively, you can provide message_text to send it instead of photo.
 * Created by edoardo on 05/04/16.
 */
public class InlineQueryResultPhoto extends InlineQueryResult{
    private String photoURL;
    private int photoWidth;
    private int photoHeight;
    private String thumbUrl;
    private String title;
    private String description;
    private String caption;
    private String messageText;
    private TelegramMethods.ParseMode parseMode;
    private boolean disableWebPagePreview;

    /**
     *
     * @param id Unique identifier for this result, 1-64 bytes
     * @param photoURL 	A valid URL of the photo. Photo must be in jpeg format. Photo size must not exceed 5MB
     * @param thumbUrl URL of the thumbnail for the photo
     */
    public InlineQueryResultPhoto(String id, String photoURL, String thumbUrl) {
        super(id, InlineQueryResultType.PHOTO);
        this.photoURL = photoURL;
        this.thumbUrl = thumbUrl;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public int getPhotoWidth() {
        return photoWidth;
    }

    public int getPhotoHeight() {
        return photoHeight;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    /**
     * Optional. Width of the photo
     * @param photoWidth
     */
    public void setPhotoWidth(int photoWidth) {
        this.photoWidth = photoWidth;
    }

    /**
     * Optional. Height of the photo
     * @param photoHeight
     */
    public void setPhotoHeight(int photoHeight) {
        this.photoHeight = photoHeight;
    }

    /**
     * Optional. Title for the result
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Optional. Short description of the result
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Optional. Caption of the photo to be sent, 0-200 characters
     * @param caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Optional. Text of a message to be sent instead of the photo, 1-4096 characters
     * @param messageText
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     *
     * @param parseMode Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
     */
    public void setParseMode(TelegramMethods.ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    /**
     * Optional. Disables link previews for links in the sent message
     * @param disableWebPagePreview
     */
    public void setDisableWebPagePreview(boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
    }
}
