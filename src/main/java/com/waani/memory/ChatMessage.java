package com.waani.memory;


import com.waani.openai.message.Message;
import com.waani.openai.message.SystemMessage;

import java.util.List;

/**
 * @author waani
 * @date 2024/9/4
 */
public abstract class ChatMessage {

    public abstract void add(Message message);

    public abstract List<Message> messages();

    protected List<Message> addMessage(Message message) {
        List<Message> messages = messages();
        Message first = messages.get(0);
        if (message instanceof SystemMessage) {
            if (!(first instanceof SystemMessage)) {
                messages.add(0, message);
            }
        } else {
            messages.add(message);
        }
        return messages;
    }

}
