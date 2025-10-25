package com.cb.common.annotation;

import java.lang.annotation.*;

/**
 * 涉密等级数据过滤注解
 *
 * @author ouyang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecretDataScope {
    /**
     * 查询时，密级实体属性字段
     */
    public String field() default "";
}
