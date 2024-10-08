package com.waani.openai.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * @author waani
 * @date 2024/9/2
 */
public class OpenAiInterceptor implements Interceptor {

    /**
     * 自定义请求头
     */
    private final Map<String, String> header;

    public OpenAiInterceptor(Map<String, String> header) {
        this.header = header;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (header != null) {
            final Request.Builder builder = request.newBuilder();
            header.forEach(builder::addHeader);
            request = builder.build();
        }
        return chain.proceed(request);
    }
}
