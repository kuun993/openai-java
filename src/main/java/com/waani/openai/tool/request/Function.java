package com.waani.openai.tool.request;

import lombok.Data;

/**
 * @author waani
 * @date 2024/9/2
 */
@Data
public class Function {

    private String name;

    private String description;

    private Parameters parameters;

}
