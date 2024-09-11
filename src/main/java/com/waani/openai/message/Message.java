package com.waani.openai.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author waani
 * @date 2024/9/2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Message {

    protected String role;

    protected String content;

    public enum Role {

        USER,

        ASSISTANT;
    }

}
