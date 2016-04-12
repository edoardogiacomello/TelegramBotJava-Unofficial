package com.edoardogiacomello.telegrambot.types;


/**
 * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
 */
public enum ChatType {
    PRIVATE("private"), GROUP("group"), SUPERGROUP("supergroup"), CHANNEL("channel");
    private String value;

    ChatType(String value){
    this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Generate the ChatType from a given string
     * @return the @{@link ChatType} corresponding to the given string, or the default value (PRIVATE) if not found
     */
    public static ChatType getType(String value){
        for (ChatType type: ChatType.values()) {
            if (value.equals(type.getValue())) return type;
        }
        return PRIVATE;
    }
}
