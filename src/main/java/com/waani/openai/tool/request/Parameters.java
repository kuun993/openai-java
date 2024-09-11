package com.waani.openai.tool.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author waani
 * @date 2024/9/2
 */
@Data
public class Parameters {

    private String type = "object";

    private Map<String, Map<String, Object>> properties;

    private List<String> required;

}
