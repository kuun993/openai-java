package com.waani.memory;

import com.waani.openai.message.Message;

import java.util.List;

/**
 * @author waani
 * @date 2024/9/4
 */
public abstract class ChatMessageStore {

    public abstract List<Message> list(String memoryId);

    public abstract void update(String memoryId, List<Message> messages);

    public abstract void remove(String memoryId);


}
