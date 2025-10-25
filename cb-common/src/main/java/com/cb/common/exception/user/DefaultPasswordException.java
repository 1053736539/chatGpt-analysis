package com.cb.common.exception.user;

public class DefaultPasswordException extends RuntimeException{
    public DefaultPasswordException()
    {
        super("user.password.default");
    }

}
