package com.waani.openai.message;

import java.util.Locale;

/**
 * @author waani
 * @date 2024/9/4
 */
public class SystemMessage extends Message {

    public static SystemMessage content(String content) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.content = content;
        systemMessage.role = Role.USER.name().toLowerCase(Locale.ROOT);
        return systemMessage;
    }

}
