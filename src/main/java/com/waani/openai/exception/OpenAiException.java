package com.waani.openai.exception;

/**
 * @author waani
 * @date 2024/9/4
 */
public class OpenAiException extends RuntimeException {

    public OpenAiException(String message) {
        super(message);
    }

    public OpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenAiException(Throwable cause) {
        super(cause);
    }

    public OpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
