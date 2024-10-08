package com.waani.openai.client;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.waani.openai.api.OpenAiApi;
import com.waani.openai.chat.response.ChatCompletionResponse;
import com.waani.openai.message.Message;
import com.waani.openai.message.UserMessage;
import com.waani.openai.interceptor.OpenAiInterceptor;
import lombok.Builder;
import okhttp3.OkHttpClient;
import com.waani.openai.exception.OpenAiException;
import com.waani.openai.chat.request.ChatCompletionRequest;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author waani
 * @date 2024/9/2
 */
public class OpenAiClient {

    private final OkHttpClient okHttpClient;

    private final OpenAiApi openAiApi;

    private final String baseUrl;

    private final String apiKey;

    private final String model;

    private final Double temperature;

    private final Double topP;

    private final Integer maxTokens;

    private final String user;

    private final Boolean stream;



    // ~ OkHttpClient
    private final Integer connectTimeout;

    private final Integer writeTimeout;

    private final Integer readTimeout;


    @Builder
    public OpenAiClient(String baseUrl,
                        String apiKey,
                        String model,
                        Double temperature,
                        Double topP,
                        Integer maxTokens,
                        String user,
                        Integer connectTimeout,
                        Integer writeTimeout,
                        Integer readTimeout,
                        Boolean stream
                        ) {
        if (baseUrl == null) {
            baseUrl = "https://api.openai.com";
        }                    
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.model = model;
        this.temperature = temperature;
        this.topP = topP;
        this.maxTokens = maxTokens;
        this.user = user;
        if (connectTimeout == null) {
            connectTimeout = 10;
        }
        this.connectTimeout = connectTimeout;
        if (writeTimeout == null) {
            writeTimeout = 20;
        }
        this.writeTimeout = writeTimeout;
        if (readTimeout == null) {
            readTimeout = 30;
        }
        this.readTimeout = readTimeout;
        this.okHttpClient = okHttpClient();
        this.openAiApi = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .client(this.okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory())
                .build().create(OpenAiApi.class);
        this.stream = stream;
    }

    /**
     * 初始化 OkHttpClient
     * @return
     */
    private OkHttpClient okHttpClient() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + this.apiKey);
        OpenAiInterceptor openAiInterceptor = new OpenAiInterceptor(header);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .connectTimeout(this.connectTimeout, TimeUnit.SECONDS)
                .writeTimeout(this.writeTimeout, TimeUnit.SECONDS)
                .readTimeout(this.readTimeout, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(openAiInterceptor)
                .build();
    }


    /**
     * 初始化 Converter.Factory
     * @return
     */
    private Converter.Factory converterFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }


    private ChatCompletionResponse chatCompletions(ChatCompletionRequest chatCompletionRequest) {
        final Call<ChatCompletionResponse> call = this.openAiApi.chatCompletions(chatCompletionRequest);
        try {
            Response<ChatCompletionResponse> execute = call.execute();
            return execute.body();
        } catch (IOException e) {
            throw new OpenAiException(e);
        }
    }



    // ~ public


    public ChatCompletionResponse chat(String text) {
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
        chatCompletionRequest.setModel(this.model);
        chatCompletionRequest.setMessages(Collections.singletonList(UserMessage.content(text)));
        return chatCompletions(chatCompletionRequest);
    }



    public ChatCompletionResponse chat(UserMessage userMessage) {
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
        chatCompletionRequest.setModel(this.model);
        chatCompletionRequest.setMessages(Collections.singletonList(userMessage));
        return chatCompletions(chatCompletionRequest);
    }


    public ChatCompletionResponse chat(List<Message> messages) {
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
        chatCompletionRequest.setModel(this.model);
        chatCompletionRequest.setMessages(messages);
        return chatCompletions(chatCompletionRequest);
    }




}
