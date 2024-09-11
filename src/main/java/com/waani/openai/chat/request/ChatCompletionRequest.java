package com.waani.openai.chat.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.waani.openai.message.Message;
import com.waani.openai.tool.request.Tool;

import java.util.List;
import java.util.Map;

/**
 * @author waani
 * @date 2024/9/2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ChatCompletionRequest {

    /**
     * 模型名称
     */
    private String model;

    /**
     * 对话消息
     */
    private List<Message> messages;

    /**
     * 0 到 2之间，用于调整模型生成文本时的创造性程度。
     * - 0.8较大，输出的文本更加随机
     * - 0.2较小，输出的文本更加专注、确定。
     *
     * 推荐使用 参数temperature 或者 top_p，但不同时使用
     */
    private Double temperature;

    /**
     * 一种替代温度采样的方法，称为核采样，其中模型考虑具有 top_p 概率质量的标记的结果。
     * 所以 0.1 意味着只考虑构成前 10% 概率质量的标记。
     *
     * 推荐使用 参数temperature 或者 top_p，但不同时使用
     */
    @JsonProperty("top_p")
    private Double topP;

    /**
     * n这个参数控制了API返回的候选文本的数量，即API会生成多少个可能的文本选项供用户选择。
     *
     * 注意: 由于此参数会生成许多， 因此它会快速消耗token配额，请谨慎使用。 可以使用参数 {@link #maxTokens} and {@link #stop} 控制使用量 。
     */
    private Integer n;

    /**
     * 是否流式输出
     *
     * 使用stream参数的好处在于，它允许我们按需生成文本，而不需要等到整个文本都生成完毕。
     * 这对于处理大型文本生成任务来说非常有用，因为它可以降低内存占用和网络带宽使用，并且可以更快地获得部分结果。
     */
    private Boolean stream;

    /**
     * 停止输出标识
     *
     * 用于指定在生成文本时停止生成的条件，当生成文本中包含指定的字符串或达到指定的最大生成长度时，生成过程会自动停止。
     *
     * 当stop参数包含多个值时，只要满足其中任意一个条件，生成器就会停止生成。
     *
     * 除了字符串之外，stop参数还可以接受一个整数，表示生成文本的最大长度。
     */
    private List<String> stop;

    /**
     * 生成内容的最大 tokens 数。
     *
     * prompt的token数加上max_tokens不能超过model的内容长度。
     * 大部分模型的长度为2048个tokens（最新的模型可以达到4096）。
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * 取值 -2.0 到 2.0。
     * 生成文本的内容是否使用频繁出现的词汇。
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty;

    /**
     * 取值 -2.0 到 2.0。
     * 生成文本时模型是否应该使用高频词汇。
     * 这里指的是model训练过程中定义的的高频词汇。
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;

    /**
     * 修改指定标记出现在补全中的可能性。
     *
     * 接受一个 JSON 对象,该对象将标记(由标记器指定的标记 ID)映射到相关的偏差值(-100 到 100)。
     * 从数学上讲,偏差在对模型进行采样之前添加到模型生成的 logit 中。
     * 确切效果因模型而异，但-1 和 1 之间的值应减少或增加相关标记的选择可能性;
     * 如-100 或 100 这样的值应导致相关标记的禁用或独占选择
     */

    @JsonProperty("logit_bias")
    private Map<String, Integer> logitBias;

    /**
     * 指定一个最终用户 ID，以使 API 可以根据用户的历史数据和偏好来生成文本。
     */
    private String user;

    /**
     * 指定模型必须输出的格式的对象。 将 { "type": "json_object" } 启用 JSON 模式,这可以确保模型生成的消息是有效的 JSON。
     * 重要提示:使用 JSON 模式时,还必须通过系统或用户消息指示模型生成 JSON。
     * 如果不这样做,模型可能会生成无休止的空白流,直到生成达到令牌限制,从而导致延迟增加和请求“卡住”的外观。
     * 另请注意,如果 finish_reason="length",则消息内容可能会被部分切断,这表示生成超过了 max_tokens 或对话超过了最大上下文长度。
     */
    @JsonProperty("response_format")
    private ResponseFormat responseFormat;

    /**
     * 如果指定,我们的系统将尽最大努力确定性地进行采样,以便使用相同的种子和参数进行重复请求应返回相同的结果。
     * 不能保证确定性，您应该参考 system_fingerprint 响应参数来监控后端的更改。
     */
    private Integer seed;

    /**
     * 模型可以调用的一组工具列表。目前，只支持作为工具的函数。
     * 使用此功能来提供模型可以为之生成 JSON 输入的函数列表。
     */
    private List<Tool> tools;

    /**
     * 控制模型调用哪个函数(如果有的话)。
     * none 表示模型不会调用函数，而是生成消息。
     * auto 表示模型可以在生成消息和调用函数之间进行选择。
     * 通过 {"type": "function", "function": {"name": "my_function"}} 强制模型调用该函数。
     * 如果没有函数存在,默认为 none。如果有函数存在,默认为 auto。
     */
    @JsonProperty("tool_choice")
    private Object toolChoice;

    
    
    
}
