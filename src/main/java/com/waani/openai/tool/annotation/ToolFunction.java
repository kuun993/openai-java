package com.waani.openai.tool.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ToolFunction {

    /**
     * 工具名
     *
     * @return 工具名
     */
    String name() default "";


    /**
     * 工具描述
     *
     * @return 工具描述
     */
    String description() default "";

}
