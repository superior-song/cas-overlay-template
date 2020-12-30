package com.cas.login;

import org.apache.commons.codec.digest.DigestUtils;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义数据密码加密工具类
 * @author zzg
 *
 */
public class PasswordEncryption implements PasswordEncoder{

    @Override
    public String encode(CharSequence password) {
        return DigestUtils.md5Hex(password.toString()).toUpperCase();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        // 判断密码是否存在
        if (rawPassword == null) {
            return false;
        }
        //通过md5加密后的密码
        String pass = this.encode(rawPassword.toString());
        //比较密码是否相等的问题
        return pass.equals(encodePassword);
    }

    AbstractPreAndPostProcessingAuthenticationHandler  handler;

}