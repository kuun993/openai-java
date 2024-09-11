package com.waani.openai.tool.response;

import lombok.Data;

/**
 * @author waani
 * @date 2024/9/4
 */
@Data
public class ToolCall {

    private String id;

    private Integer index;

    private String type;

    private FunctionCall function;

}
