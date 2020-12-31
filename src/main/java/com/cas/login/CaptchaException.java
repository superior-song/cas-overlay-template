package com.cas.login;

import javax.security.auth.login.AccountException;

@SuppressWarnings("serial")
public class CaptchaException extends AccountException {
    public CaptchaException() {
        super();
    }

    public CaptchaException(String msg) {
        super(msg);
    }
}