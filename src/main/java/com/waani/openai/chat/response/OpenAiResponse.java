package com.waani.openai.chat.response;

import lombok.Data;

/**
 * @author waani
 * @date 2024/9/4
 */
@Data
public class OpenAiResponse<T> {

    private T content;

    private Usage usage;

    private String finishReason;

}
