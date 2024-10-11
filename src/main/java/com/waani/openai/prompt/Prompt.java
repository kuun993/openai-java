package com.waani.openai.prompt;

import com.waani.openai.message.Message;
import java.util.Collections;
import java.util.List;

/**
 * @author waani
 * @date 2024/10/11
 */
public class Prompt {

    private final List<Message> messages; 

    public Prompt(Message message) {
        this(Collections.singletonList(message));
    }

    public Prompt(List<Message> messages) {
        this.messages = messages;
    }

}
