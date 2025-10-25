package com.cb.common.annotation;

import com.cb.common.enums.EncryptType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 加解密注解,sourceField  不为空时只进行mac 计算
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EncryptField {
    /**
     * 值字段名称
     *
     * @return
     */
    public String [] macSourceField() default {};

    /**
     * 加密 选择  默认sm3 加密
     * 1. sm3 只提供加密
     * 2. sm4 提供加密和解密
     *
     * @return
     */
    public EncryptType fieldType() default EncryptType.SM3;

}
