package com.cb.common.encipher.service;

public interface IEncipherService {
    /**
     * SM3 加密
     *
     * @param plaintext 明文
     * @return
     */
    public String sm3Encrypt(String plaintext);

    /**
     * SM3 校验
     *
     * @param plaintext  明文
     * @param ciphertext 密文
     * @return
     */
    public boolean sm3Verify(String plaintext, String ciphertext);


    /**
     * SM4 加密
     * @param plaintext 明文
     * @return
     */
    public String sm4EncryptEcb(String plaintext);

    /**
     * SM4 解密
     * @param cipherText
     * @return
     */
    public String sm4DecryptEcb(String cipherText);
}
