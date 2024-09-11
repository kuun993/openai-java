package com.waani.memory.impl;

import com.waani.memory.ChatMessageStore;
import com.waani.openai.message.Message;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author waani
 * @date 2024/9/4
 */
//@Slf4j
public class DefaultChatMessageStore extends ChatMessageStore implements Serializable {

    final List<Message> messages = new ArrayList<>();

    @Override
    public List<Message> list(String memoryId) {
        return messages;
    }

    @Override
    public void update(String memoryId, List<Message> messages) {
        remove(memoryId);
        this.messages.addAll(messages);
    }

    @Override
    public void remove(String memoryId) {
        messages.clear();
    }

}
