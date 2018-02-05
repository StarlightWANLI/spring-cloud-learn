package com.hhly.jpa.springdatajpa.util;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 加密、解密工具类
 */
public class EncryptUtils {
    // 密钥    jasypt.encryptor.password
    private static final String KEY = "hhly2017";

    public static void main(String[] args) {
        String ciphertext1 = encrypt("123456"); // Wu11fsC0gpgSET5aU8GXUA==
        String ciphertext2 = encrypt("abcdefg"); // ESXlHsVk2YM7mGcHy2ccGg==
        System.out.println(ciphertext1);
        System.out.println(ciphertext2);

        String text1 = decrypt(ciphertext1);
        String text2 = decrypt(ciphertext2);
        System.out.println(text1);               // abcdefg
        System.out.println(text2);               // abcdefg
    }

    /**
     * 加密
     * @param text 明文
     * @return     密文
     */
    public static String encrypt(String text) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.encrypt(text);
    }

    /**
     * 解密
     * @param ciphertext 密文
     * @return           明文
     */
    public static String decrypt(String ciphertext) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(KEY);
        return encryptor.decrypt(ciphertext);
    }
}
