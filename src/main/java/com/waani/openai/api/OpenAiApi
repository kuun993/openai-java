package com.waani.openai.api;

import com.waani.openai.chat.request.ChatCompletionRequest;
import com.waani.openai.chat.response.ChatCompletionResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author waani
 * @date 2024/9/2
 */
public interface OpenAiApi {

    @POST("chat/completions")
    @Headers({"Content-Type: application/json"})
    Call<ChatCompletionResponse> chatCompletions(
            @Body ChatCompletionRequest chatCompletionRequest
    );

}
