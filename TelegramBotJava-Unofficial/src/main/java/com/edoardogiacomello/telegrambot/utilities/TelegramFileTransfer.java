package com.edoardogiacomello.telegrambot.utilities;

import com.edoardogiacomello.telegrambot.types.TelegramFile;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class contains helper methods for basic file transfer from the Telegram Servers
 * Created by edoardo on 12/04/16.
 */
public class TelegramFileTransfer {
    /**
     * Returns the URL for a telegramFile given a TelegramFile object and a bot token, or null if the information are missing
     * @param telegramFile
     * @param token
     * @return
     */
    public static String getFileUrl(TelegramFile telegramFile, String token){
        if (telegramFile == null || telegramFile.getFilePath() == null || token == null) {
            return null;
        }
        return "https://api.telegram.org/file/bot"+token+"/"+ telegramFile.getFilePath()+"?file_id="+telegramFile.getFileId();
    }

    public static File getFile(TelegramFile telegramFile, String token) throws IOException {
        File file = new File("BotTempFile");
        if(!file.exists())
        {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String url = getFileUrl(telegramFile, token);
            //Debug
            System.out.println("Getting file from: " + url);
            HttpGet httpget = new HttpGet(url);
            //httpget.setHeader("Content-Type", "text/html");

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                fileOutputStream.write(EntityUtils.toByteArray(response.getEntity()));
                fileOutputStream.close();
            } finally {
                response.close();
            }
            //Debug

            System.out.println(response.toString());
        } finally {
            httpclient.close();
        }
        return file;
    }
}
