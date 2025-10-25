package com.cb.common.encipher.utils;

import com.cb.common.encipher.utils.SM2Util;
import com.cb.common.encipher.utils.SM3Util;
import com.cb.common.encipher.utils.SM4Util;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

public class SMCryptoUtils {
    private static SM2Util sm2Util = new SM2Util();
    private static SM3Util sm3Util = new SM3Util();
    private static SM4Util sm4Util = new SM4Util();


    public static SM2Util sm2Util() {
        return sm2Util;
    }

    public static SM3Util sm3Util() {
        return sm3Util;
    }

    public static SM4Util sm4Util() {
        return sm4Util;
    }

    /**
     * sm2 公钥加密
     *
     * @param param     需要加密字符
     * @param publicKey 公钥
     * @return 返回字节数组
     */
    public static byte[] sm2Encrypt(String param, ECPoint publicKey) {
        return sm2Util.encrypt(param, publicKey);
    }

    /**
     * sm2私钥解密
     *
     * @param encryptData 密文
     * @param privateKey  私钥
     * @return 返回解密后字符
     */
    public static String sm2Decrypt(byte[] encryptData, BigInteger privateKey) {
        return sm2Util.decrypt(encryptData, privateKey);
    }

    /**
     * sm3算法加密
     *
     * @param param 待加密字符串
     * @return 返回加密后，固定长度=32的16进制字符串
     * @explain
     */
    public static String sm3Encrypt(String param) {
        return sm3Util.encrypt(param);
    }

    /**
     * 判断源数据与加密数据是否一致
     *
     * @param srcStr       原字符串
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     * @explain 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
     */
    public static boolean sm3Verify(String srcStr, String sm3HexString) {
        return sm3Util.verify(srcStr, sm3HexString);
    }

    /**
     * 系统生成密钥加密
     *
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @explain 加密模式：ECB
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String sm4EncryptEcbAuto(String paramStr) {
        try {
            return sm4Util.encryptEcbAuto(paramStr);
        } catch (Exception e) {
            return paramStr;
        }
    }

    /**
     * sm4加密   自己提供16进制的密钥 进行加密
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     * @explain 加密模式：ECB
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String sm4EncryptEcb(String hexKey, String paramStr) {
        try {
            return sm4Util.encryptEcb(hexKey, paramStr);
        } catch (Exception e) {
            return paramStr;
        }

    }

    /**
     * sm4解密
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用ECB
     */
    public static String sm4DecryptEcb(String hexKey, String cipherText) {
        try {
            return sm4Util.decryptEcb(hexKey, cipherText);
        } catch (Exception e) {
            return cipherText;
        }

    }

    /**
     * 校验加密前后的字符串是否为同一数据
     *
     * @param hexKey     16进制密钥（忽略大小写）
     * @param cipherText 16进制加密后的字符串
     * @param paramStr   加密前的字符串
     * @return 是否为同一数据
     * @throws Exception
     */
    public static boolean sm4VerifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        return sm4Util.verifyEcb(hexKey, cipherText, paramStr);
    }

    /**
     * 生成32位16进制key
     *
     * @return 返回sm4 获取的32位16进制key
     */
    public static String sm4Generate32Key() {
        try {
            byte[] bytes = sm4Util.generateKey();
            // byte[]-->32位16进制字符串
            return sm4Util.parseByte2HexStr(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
