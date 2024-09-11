package com.waani;

import com.waani.openai.client.OpenAiClient;
import com.waani.openai.chat.response.ChatCompletionResponse;

/**
 * @author waani
 * @date 2024/9/2
 */
public class Main {


    public static void main(String[] args) {
        OpenAiClient openAiClient = OpenAiClient.builder()
                .baseUrl("https://api.proxyxai.com/v1/")
                .model("gpt-4o-mini-2024-07-18")
                .build();

        final ChatCompletionResponse openAiResponse = openAiClient.chat("你会做什么？");
        System.out.println(openAiResponse);
        System.exit(0);
    }



}
