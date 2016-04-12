package com.edoardogiacomello.telegrambot.types.inline.results;

import com.edoardogiacomello.telegrambot.methods.TelegramMethods;
import org.json.JSONObject;

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
    private TelegramMethods.ParseMode parseMode;
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

    public TelegramMethods.ParseMode getParseMode() {
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

    public void setParseMode(TelegramMethods.ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    public void setDisableWebPagePreview(boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
    }
    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", this.type.getValue());
        jsonObject.put("id",this.id);
        jsonObject.put("mpeg4_url",this.mpeg4URL);
        if(mpeg4Width>0)
            jsonObject.put("mpeg4_width",this.mpeg4Width);
        if(mpeg4Height>0)
            jsonObject.put("mpeg4_height",this.mpeg4Height);

        jsonObject.put("thumb_url",this.thumbURL);
        if (title!=null)
            jsonObject.put("title",this.title);
        if(caption!=null)
            jsonObject.put("caption",this.caption);
        if(messageText!=null)
            jsonObject.put("message_text",this.messageText);
        if(parseMode != null)
            jsonObject.put("parse_mode",this.parseMode.getValue());
        jsonObject.put("disable_web_page_preview",this.disableWebPagePreview);
        return jsonObject;
    }
}


