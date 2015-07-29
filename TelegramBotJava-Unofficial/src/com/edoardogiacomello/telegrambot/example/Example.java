package com.edoardogiacomello.telegrambot.example;

import java.util.List;
import java.util.Scanner;

import com.edoardogiacomello.telegrambot.main.TelegramBot;
import com.edoardogiacomello.telegrambot.main.TelegramEvents;
import com.edoardogiacomello.telegrambot.types.Message;
import com.edoardogiacomello.telegrambot.types.Update;
import com.edoardogiacomello.telegrambot.types.User;

public class Example {

	private void startBot(String authToken){
		//Instantiate a new bot 
		TelegramBot bot= new TelegramBot(authToken,true);
		
		User botInfo = bot.getMe();
		
		//Receiving updates: Method 1
		List<Update> receivedUpdates = bot.getUpdates();
		for (Update newUpdate : receivedUpdates) {
			//Do Something with your update
		}
		
		//Receiving updates: Method 2
		long pollingDelayMs = 2000;
		try {
			TelegramEvents updateHandler = new TelegramEvents() {
				
				@Override
				public void onUpdate(Update update) {
					//Do something with your update, this is useful if you want control over update id
				}
				
				@Override
				public void onMessageReceived(Message message) {
					//Do something with your message from the user
				}
			};
			//Register the handler
			bot.registerForUpdates(updateHandler);
			//Start a new polling thread
			bot.startPolling(pollingDelayMs);
		} finally {
			//When done, remember to stop the polling thread
			bot.stopPolling();
		}
		
		
		
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Insert your Authorization Token: \n");
		String authToken = s.nextLine();
		Example ex = new Example();
		ex.startBot(authToken);
		}

}
