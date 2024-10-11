package com.waani.openai.message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Locale;

/**
 * @author waani
 * @date 2024/9/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserMessage extends Message {

    public static UserMessage content(String content) {
        UserMessage userMessage = new UserMessage();
        userMessage.content = content;
        userMessage.role = Role.USER.name().toLowerCase(Locale.ROOT);
        return userMessage;
    }

}
