package com.waani.openai.tool.request;

import lombok.Data;

/**
 * @author waani
 * @date 2024/9/2
 */
@Data
public class Function {

    /**
     * 工具名称
     */
    private String name;

    /**
     * 工具描述
     */
    private String description;

    /**
     * 工具参数
     */
    private Parameters parameters;

}
