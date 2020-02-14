package com.moon.demo.annotation;

import java.lang.annotation.*;

/**
 * @Description:自定义注解
 * @Author：xiaojiaxin
 * @Date：2020-01-14 22:51
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldName {

    String value() default "这是小月的第一个自定义注解";

}
