package com.waani.openai.chat.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waani.openai.message.AssistantMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author waani
 * @date 2024/9/2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CompletionChoice {

    private String text;

    private Integer index;

    private String finishReason;

    private AssistantMessage assistantMessage;


}
