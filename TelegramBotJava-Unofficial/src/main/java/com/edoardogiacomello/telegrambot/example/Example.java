package com.edoardogiacomello.telegrambot.example;

import com.edoardogiacomello.telegrambot.main.TelegramBot;
import com.edoardogiacomello.telegrambot.main.TelegramEvents;
import com.edoardogiacomello.telegrambot.types.*;
import com.edoardogiacomello.telegrambot.types.inline.InlineQuery;
import com.edoardogiacomello.telegrambot.types.inline.results.InlineQueryResult;
import com.edoardogiacomello.telegrambot.types.inline.results.InlineQueryResultArticle;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Example {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Insert your Authorization Token: \n");
        String authToken = s.nextLine();
        Example ex = new Example();
        ex.startBot(authToken);
    }

    private void startBot(String authToken) {
        //Instantiate a new bot
        TelegramBot bot = new TelegramBot(authToken, true);

        //User botInfo = bot.getMe();

        //Receiving updates: Method 1
        //List<Update> receivedUpdates = bot.getUpdates();
        /*
		for (Update newUpdate : receivedUpdates) {
			//Do Something with your update
		}
		*/

        //Receiving updates: Method 2
        long pollingDelayMs = 200;
        try {
            TelegramEvents updateHandler = new TelegramEvents() {

                @Override
                public void onUpdate(Update update) {
                    //Do something with your update, this is useful if you want control over update id
                    //System.out.println("Got an update");
                }

                @Override
                public void onMessageReceived(Message message) {
                    //Do something with your message from the user
                    bot.sendMessage(message.getChat().getChatId(), "Message Text", false, 0, null);
                }

                @Override
                public void onTextMessageReceived(Message message, String text) {
                    System.out.println("Text received");
                }

                @Override
                public void onPhotoReceived(Message message, List<PhotoSize> photos, String caption) {
                    System.out.println("Photo received");
                }

                @Override
                public void onAudioReceived(Message message, Audio audio) {
                    System.out.println("Audio received");
                }

                @Override
                public void onDocumentReceived(Message message, Document document) {
                    System.out.println("Document received");
                }

                @Override
                public void onStickerReceived(Message message, Sticker sticker) {
                    System.out.println("Sticker received");
                }

                @Override
                public void onVideoReceived(Message message, Video video) {
                    System.out.println("Video received");
                }

                @Override
                public void onVoiceReceived(Message message, Voice voice) {
                    System.out.println("Voice received");
                }

                @Override
                public void onLocationReceived(Message message, Location location) {
                    System.out.println("Location received");
                }

                @Override
                public void onInlineQueryReceived(InlineQuery inlineQuery) {
                    System.out.println("Inline Query Received: " + inlineQuery.getQuery());
                    InlineQueryResultArticle article = new InlineQueryResultArticle(new BigInteger(130, new SecureRandom()).toString(32), inlineQuery.getQuery(), inlineQuery.getQuery());
                    List<InlineQueryResult> results = new ArrayList<>();
                    results.add(article);
                    bot.answerInlineQuery(inlineQuery.getId(), results, 0, false, "");
                }

            };
            //Register the handler
            bot.registerForUpdates(updateHandler);
            //Start a new polling thread
            bot.startPolling(pollingDelayMs);
            Scanner s = new Scanner(System.in);
            System.out.println("Bot Running, press Enter to stop the bot.");
            s.nextLine();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        } finally {
            //When done, remember to stop the polling thread
            bot.stopPolling();
        }


    }

}
