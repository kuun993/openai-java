package com.waani.openai.chat.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author waani
 * @date 2024/9/2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Usage {

    /**
     * promptTokens
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    /**
     * completionTokens
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    /**
     * totalTokens
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;


    @Builder
    public Usage(Integer promptTokens, Integer completionTokens, Integer totalTokens) {
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
    }


}
