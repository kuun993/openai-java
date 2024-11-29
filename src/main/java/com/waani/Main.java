package com.waani;

import com.waani.openai.client.OpenAiClient;
import com.waani.common.Constants;
import com.waani.openai.chat.response.ChatCompletionResponse;
import com.waani.openai.message.Message;
import com.waani.openai.message.UserMessage;
import com.waani.openai.tool.annotation.Param;
import com.waani.openai.tool.annotation.ToolFunction;
import com.waani.openai.tool.enums.ParamType;
import com.waani.openai.utils.ToolUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waani
 * @date 2024/9/2
 */
public class Main {


    public static void main(String[] args) {
        OpenAiClient openAiClient = OpenAiClient.builder()
                .baseUrl(Constants.OPENAI_BASE_URL)
                .apiKey(Constants.OPENAI_API_KEY)
                .model("gpt-4o-mini-2024-07-18")
                .build();
        List<Message> messages = new ArrayList<>();
        messages.add(UserMessage.content("广州天气？"));
        final ChatCompletionResponse openAiResponse = openAiClient.chat(messages, ToolUtil.toTools(ToolDemo.class));
        System.out.println(openAiResponse);
        System.exit(0);
    }




    public static class ToolDemo {

        @ToolFunction(name = "weather", description = "获取天气")
        public String weather(@Param(type = ParamType.STRING, description = "城市", required = true) String city) {
            return "weather";
        }

    }

}
