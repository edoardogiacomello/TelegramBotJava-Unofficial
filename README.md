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
For implementing a telegram bot it is necessary to have an auth Token provided by the BotFather.


###Creating a new bot
Instantiate a new TelegramBot. The first parameter is the auth Token provided by the BotFather, the second is a boolean which simply tells whether the bot have to consume the messages once a reply is send. Setting it to false will cause to receive the whole list of updates at each update request.

	 TelegramBot bot= new TelegramBot(authToken,true);

###Receiving Updates
You can choose of receiving updates from the server in two ways. In both cases it implies to implement a set of methods regarding the case of interest.
####Method 1: Asynchronous updates (Default)

All you have to do is to implement the members of the event handler for specifying your bot behaviour (methods are omitted for simplicity):



			TelegramEvents updateHandler = new TelegramEvents() {

                    @Override
                    public void onUpdate(Update update) {
                        //This function is for advanced or debug purposes
                    }

                    @Override
                    public void onMessageReceived(Message receivedMessage) {
                        //This is called for all types of messages
                        }

                   @Override
                   public void onTextMessageReceived(Message message, String text) {
                       //This is called for text messages
                   }
               ...
			};

Then we have to start the bot.
This method uses short polling to make requests to the server, it will check for new updates at a fixed time interval.
When new Updates are found, they are sent back to the appropriate methods of the event handler.
Notice that this method will run in a new thread.

        //Set the polling delay in ms
        long pollingDelayMs = 2000;
        try {
			//Register the events handler
			bot.registerForUpdates(updateHandler);
			//Start a new polling thread
			bot.startPolling(pollingDelayMs);
		} finally {
			//When done, **remember to stop the polling thread**
			bot.stopPolling();
		}

####Method 2: Synchronous updates
Use this method if you need to receive updates all at a time. Notice that this method will run on the same thread you call it.

	List<Update> receivedUpdates = bot.getUpdates();
		for (Update newUpdate : receivedUpdates) {
			//Do Something with your update
		}

###Calling methods
This is how it is possible to reply to the user. For the detailed description of all the parameters please check the attached JavaDoc or the Telegram Bot Api Documentation

    bot.sendMessage(receivedMessage.getChat().getChatId(), "Message Text", false, 0, null);

