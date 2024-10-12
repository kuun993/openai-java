
## Getting Started

```java

    OpenAiClient openAiClient = OpenAiClient.builder()
            .baseUrl(Constants.OPENAI_BASE_URL) // base_url
            .apiKey(Constants.OPENAI_API_KEY)   // api_key
            .model("gpt-4o-mini")    // model
            .build();

    final ChatCompletionResponse openAiResponse = openAiClient.chat("你会做什么？");
    System.out.println(openAiResponse.getMessage());

```