package com.cb.system.util;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2025-02-14 10:08
 * @Version: 1.0
 **/
public class WordReadFieldException extends RuntimeException{
    public WordReadFieldException() {
    }

    public WordReadFieldException(String message) {
        super(message);
    }

    public WordReadFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public WordReadFieldException(Throwable cause) {
        super(cause);
    }

    public WordReadFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
