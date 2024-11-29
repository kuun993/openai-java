package com.waani.openai.utils;

import com.waani.openai.tool.annotation.Param;
import com.waani.openai.tool.annotation.ToolFunction;
import com.waani.openai.tool.request.Function;
import com.waani.openai.tool.request.Parameters;
import com.waani.openai.tool.request.Tool;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ToolUtil {

    private ToolUtil() {}


    public static List<Tool> toTools(Class<?> clazz) {
        final Method[] methods = clazz.getMethods();
        List<Tool> tools = new ArrayList<>();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(ToolFunction.class)) {
                continue;
            }
            final Tool tool = toTool(method);
            if (tool != null) {
                tools.add(tool);
            }
        }
        return tools;
    }


    /**
     * 将方法转换为工具
     * @param method 方法
     * @return 工具
     */
    public static Tool toTool(Method method) {
        final ToolFunction toolFunction = method.getAnnotation(ToolFunction.class);
        if (toolFunction == null) {
            return null;
        }
        Tool tool = new Tool();
        Function function = new Function();
        tool.setFunction(function);
        function.setName(toolFunction.name());
        function.setDescription(toolFunction.description());
        // 获取方法参数
        Parameters parameters = new Parameters();
        function.setParameters(parameters);
        Map<String, Map<String, Object>> properties = new HashMap<>();
        parameters.setProperties(properties);
        final Parameter[] methodParameters = method.getParameters();
        List<String> required = new ArrayList<>();
        parameters.setRequired(required);
        for (Parameter parameter : methodParameters) {
            final Param param = parameter.getAnnotation(Param.class);
            if (param == null) {
                continue;
            }
            Map<String, Object> property = new HashMap<>();
            properties.put(parameter.getName(), property);
            property.put("type", param.type().name().toLowerCase());
            property.put("description", param.description());
            if (param.enums() != null && param.enums().length > 0) {
                property.put("enum", param.enums());
            }
            if (param.required()) {
                required.add(parameter.getName());
            }
        }
        return tool;
    }

}
