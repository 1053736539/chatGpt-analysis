package com.cb.common.encipher.service.impl;

import com.cb.common.encipher.service.IEncipherService;
import com.cb.common.encipher.utils.SMCryptoUtils;
import org.springframework.beans.factory.annotation.Value;

public class LocalEncipherServiceImpl implements IEncipherService {

    @Value("${encipher.local-serve.sm4-SecretKey}")
    private String secretKey;

    @Override
    public String sm3Encrypt(String plaintext) {
        return SMCryptoUtils.sm3Encrypt(plaintext);
    }

    @Override
    public boolean sm3Verify(String plaintext, String ciphertext) {
        return SMCryptoUtils.sm3Verify(plaintext, ciphertext);
    }

    @Override
    public String sm4EncryptEcb(String plaintext) {
        return SMCryptoUtils.sm4EncryptEcb( secretKey,plaintext);
    }

    @Override
    public String sm4DecryptEcb(String cipherText) {
        return SMCryptoUtils.sm4DecryptEcb( secretKey,cipherText);
    }
}
