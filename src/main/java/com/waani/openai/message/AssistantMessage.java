package com.waani.openai.message;

import com.waani.openai.tool.response.ToolCall;
import lombok.Data;

import java.util.List;
import java.util.Locale;

/**
 * @author waani
 * @date 2024/9/4
 */
@Data
public class AssistantMessage extends Message {

    private String name;

    private List<ToolCall> toolCalls;

    public static AssistantMessage content(String content) {
        AssistantMessage assistantMessage = new AssistantMessage();
        assistantMessage.content = content;
        assistantMessage.role = Message.Role.ASSISTANT.name().toLowerCase(Locale.ROOT);
        return assistantMessage;
    }

}
