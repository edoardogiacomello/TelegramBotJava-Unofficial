# TelegramBotJava-Unofficial
a Java wrapper for Telegram Bot Web APIs
## General info
This project will let you set up a Telegram Bot with a few lines of java code. 
Notice that at this time of developement the file upload is not available, so you can send other users only files that are currently on Telegram servers.
For everything that is not described on this page or in the attached JavaDoc you can refer to the official [Telegram Bot APIs](https://core.telegram.org/bots/api/). Keep in mind that the method and Type names have been kept as similas as possible to the ones provided by **Telegram Web APIs**. 
If you plan to use this project in your work, you simply like it or you want to contribute please let me know at [edoardo.giacomello1990@gmail.com](mailto: edoardo.giacomello1990@gmail.com)
## Import 
You can find a .zip file containing this and all the necessary dependencies [here](https://github.com/edoardogiacomello/TelegramBotJava-Unofficial/blob/master/TelegramBotJava-Unofficial-0.1d.zip?raw=true). Please make sure to import all the jar files found within the zip archive into your project.

##Usage
###Creating a new bot
	 TelegramBot bot= new TelegramBot(authToken,true);
###Calling methods - Example: Getting bot info
	 User botInfo = bot.getMe();
###Receiving Updates
You can choose of receiving updates from the server in two ways:
#### Method 1: Synchronous updates
Use this method if you need to receive updates at a pre-determined moment. Notice that this method will run on the same thread you call it.
	List<Update> receivedUpdates = bot.getUpdates();
		for (Update newUpdate : receivedUpdates) {
			//Do Something with your update
		}
####Method 2: Asynchronous updates
This method uses short polling to make requests to the server and an event handler for receiving updates.
Notice that this method will run in a new thread. An event will be fired every time a new Update is available:
	//Set the polling delay in ms
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
			//When done, **remember to stop the polling thread**
			bot.stopPolling();
		}
