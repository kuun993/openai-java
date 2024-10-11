package com.waani.openai.prompt;

import com.waani.openai.message.Message;
import com.waani.openai.message.UserMessage;

/**
 * @author waani
 * @date 2024/10/11
 */
public class Prompt<T extends Message> {

    private final List<T> messages; 


    public Prompt(String message) {
        this(UserMessage.content(message));
    }

    public Prompt(T message) {
        this(Collections.singletonList(message));
    }

    public Prompt(List<T> messages) {
        this.messages = messages;
    }

}
