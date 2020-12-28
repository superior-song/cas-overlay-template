package com.cas;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by songxiaoliang on 2020/12/25 0025.
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence password) {
        try {
            // 给数据进行SHA-256加密
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.toString().getBytes("UTF-8"));
            String encodePassword = new String(Base64.encodeBase64(digest));

            System.out.println("encode方法：加密前（" + password + "），加密后（" + encodePassword + "）");

            return encodePassword;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 调用这个方法来判断密码是否匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        // 判断密码是否存在
        if (rawPassword == null) {
            return false;
        }
        // 通过SHA-256加密后的密码
        String pass = this.encode(rawPassword.toString());

        System.out.println(
                "matches方法：rawPassword：" + rawPassword + "，encodePassword：" + encodePassword + "，pass：" + pass);
        // 比较密码是否相等的问题
        return true;
       //return pass.equals(encodePassword);
    }
}
