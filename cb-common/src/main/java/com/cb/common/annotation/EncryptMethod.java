package com.cb.common.annotation;

import com.cb.common.enums.EODType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 安全字段注解
 * 加在需要加密/解密的方法上
 * 实现自动加密解密
 *
 * @author: ouyan
 * @date:2021/04/20
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EncryptMethod {
    /**
     * 参数
     *
     * @return
     */
    public String[] argNames() default {};

    /**
     * 默认第一个参数
     * @return
     */
    public int argIndex() default 0;

    /**
     * 默认加密
     * 功能 : 包含加密保存和解密
     */
    public EODType businessType() default EODType.ENCRYPT;
}