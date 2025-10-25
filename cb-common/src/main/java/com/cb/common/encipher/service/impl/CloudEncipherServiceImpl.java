package com.cb.common.encipher.service.impl;

import com.cb.common.encipher.service.IEncipherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudEncipherServiceImpl implements IEncipherService {
    private static final Logger log = LoggerFactory.getLogger(CloudEncipherServiceImpl.class);

    @Override
    public String sm3Encrypt(String plaintext) {
        return null;
    }

    @Override
    public boolean sm3Verify(String plaintext, String ciphertext) {
        return false;
    }

    @Override
    public String sm4EncryptEcb(String plaintext) {
        return null;
    }

    @Override
    public String sm4DecryptEcb(String cipherText) {
        return null;
    }
}
