package com.waani.memory.impl;

import com.waani.memory.ChatMessage;
import com.waani.memory.ChatMessageStore;
import com.waani.openai.message.Message;
import lombok.Builder;

import java.util.List;

/**
 * @author waani
 * @date 2024/9/4
 */
public class MessageWindowChatMessage extends ChatMessage {

    private final String memoryId;

    private final Integer maxMessages;

    private final ChatMessageStore chatMessageStore;

    private static final int DEFAULT_MAX_MESSAGES = 10;

    @Builder
    public MessageWindowChatMessage(String memoryId, Integer maxMessages, ChatMessageStore chatMessageStore) {
        this.memoryId = memoryId;
        if (chatMessageStore == null) {
            chatMessageStore = new DefaultChatMessageStore();
        }
        this.chatMessageStore = chatMessageStore;
        if (maxMessages == null) {
            maxMessages = DEFAULT_MAX_MESSAGES;
        }
        this.maxMessages = maxMessages;
    }

    @Override
    public void add(Message message) {
        List<Message> messages = addMessage(message);
        chatMessageStore.update(memoryId, messages);
    }

    @Override
    public List<Message> messages() {
        return chatMessageStore.list(memoryId);
    }


    /**
     * 保留至多 {@link #maxMessages} 条消息
     */
    private void ensureCapacity(List<Message> messages) {
        // TODO
    }

}
