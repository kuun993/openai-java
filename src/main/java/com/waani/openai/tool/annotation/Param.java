package com.waani.openai.tool.annotation;


import com.waani.openai.tool.enums.ParamType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {

    ParamType type() default ParamType.STRING;

    /**
     * 参数的描述
     */
    String description() default "";

    /**
     * 枚举
     * @return  枚举
     */
    String[] enums() default {};

    /**
     * 参数是否必填
     */
    boolean required() default false;

}
