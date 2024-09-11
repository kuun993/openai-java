package com.waani.openai.tool.response;

import lombok.Data;

/**
 * @author waani
 * @date 2024/9/4
 */
@Data
public class FunctionCall {

    private String name;

    private String arguments;

}
