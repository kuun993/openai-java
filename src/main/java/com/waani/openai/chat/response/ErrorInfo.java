package com.waani.openai.chat.response;

import lombok.Data;

/**
 * @author waani
 * @date 2024/10/8
 */
@Data
public class ErrorInfo {

    private int code;

    private String message;

}
