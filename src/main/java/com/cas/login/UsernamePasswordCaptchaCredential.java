package com.cas.login;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

/**
 * 自定义用户凭证，添加验证码属性
 * @author sxl
 *
 */
@SuppressWarnings("serial")
public class UsernamePasswordCaptchaCredential extends RememberMeUsernamePasswordCredential {

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.captcha)
                .toHashCode();
    }
}


