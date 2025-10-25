package com.cb.common.encipher.encoder;


import com.cb.common.encipher.utils.SMCryptoUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ruoyi
 * 使用SM 加密算法实现
 * 指定使用密码验证类
 */
public class EncipherPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else {
            return SMCryptoUtils.sm3Encrypt(rawPassword.toString());
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            return SMCryptoUtils.sm3Verify(rawPassword.toString(),encodedPassword);
        }
        return false;
    }
}
