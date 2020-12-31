package com.suncreate.exception;

import javax.security.auth.login.AccountException;

/**
 * @author: songxiaoliang
 * @date: 2020/8/31
 * @description: 用户没找到异常
 */
public class MyAccountNotFoundException extends AccountException {

    private static final long serialVersionUID = 1L;

    public MyAccountNotFoundException() {
        super();
    }

    public MyAccountNotFoundException(String msg) {
        super(msg);
    }
}