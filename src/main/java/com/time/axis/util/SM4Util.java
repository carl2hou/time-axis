package com.time.axis.util;

import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 国密SM4加密工具类
 * 支持ECB/CBC两种模式，默认使用CBC模式
 */
public class SM4Util {

    static {
        // 添加BouncyCastleProvider
        Security.addProvider(new BouncyCastleProvider());
    }

    // 算法名称
    private static final String ALGORITHM_NAME = "SM4";
    // 默认模式：CBC
    private static final String DEFAULT_MODE = "CBC";
    // 密钥长度（128位）
    private static final int KEY_SIZE = 128;

    /**
     * 生成SM4密钥
     * @return 16字节的HEX格式密钥
     */
    public static String generateKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(KEY_SIZE, new SecureRandom());
        SecretKey secretKey = kg.generateKey();
        return Hex.toHexString(secretKey.getEncoded());
    }

    /**
     * SM4加密
     * @param plainText 明文
     * @param key       HEX格式密钥
     * @return HEX格式密文
     */
    public static String encrypt(String plainText, String key) throws Exception {
        return encrypt(plainText, key, DEFAULT_MODE);
    }

    /**
     * SM4加密（指定模式）
     * @param plainText 明文
     * @param key       HEX格式密钥
     * @param mode      模式：ECB或CBC
     * @return HEX格式密文
     */
    public static String encrypt(String plainText, String key, String mode) throws Exception {
        byte[] keyBytes = Hex.decode(key);
        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);

        if ("ECB".equalsIgnoreCase(mode)) {
            return Hex.toHexString(encryptEcb(plainBytes, keyBytes));
        } else {
            // CBC模式需要生成IV
            byte[] iv = generateIv();
            byte[] encrypted = encryptCbc(plainBytes, keyBytes, iv);
            // 返回格式: IV + 密文
            return Hex.toHexString(iv) + Hex.toHexString(encrypted);
        }
    }

    /**
     * SM4解密
     * @param cipherText HEX格式密文
     * @param key        HEX格式密钥
     * @return 明文
     */
    public static String decrypt(String cipherText, String key) throws Exception {
        return decrypt(cipherText, key, DEFAULT_MODE);
    }

    /**
     * SM4解密（指定模式）
     * @param cipherText HEX格式密文
     * @param key        HEX格式密钥
     * @param mode       模式：ECB或CBC
     * @return 明文
     */
    public static String decrypt(String cipherText, String key, String mode) throws Exception {
        byte[] keyBytes = Hex.decode(key);
        byte[] cipherBytes = Hex.decode(cipherText);

        if ("ECB".equalsIgnoreCase(mode)) {
            return new String(decryptEcb(cipherBytes, keyBytes), StandardCharsets.UTF_8);
        } else {
            // 提取IV (前16字节)
            byte[] iv = Hex.decode(cipherText.substring(0, 32));
            byte[] encrypted = Hex.decode(cipherText.substring(32));
            return new String(decryptCbc(encrypted, keyBytes, iv), StandardCharsets.UTF_8);
        }
    }

    // ================ 核心加解密方法 ================

    private static byte[] encryptEcb(byte[] input, byte[] key) throws Exception {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new SM4Engine(), new ZeroBytePadding());
        cipher.init(true, new KeyParameter(key));
        return process(cipher, input);
    }

    private static byte[] decryptEcb(byte[] input, byte[] key) throws Exception {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new SM4Engine(), new ZeroBytePadding());
        cipher.init(false, new KeyParameter(key));
        return process(cipher, input);
    }

    private static byte[] encryptCbc(byte[] input, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("SM4/CBC/PKCS7Padding", BouncyCastleProvider.PROVIDER_NAME);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        return cipher.doFinal(input);
    }

    private static byte[] decryptCbc(byte[] input, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("SM4/CBC/PKCS7Padding", BouncyCastleProvider.PROVIDER_NAME);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        return cipher.doFinal(input);
    }

    // ================ 工具方法 ================

    private static byte[] generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    private static byte[] process(PaddedBufferedBlockCipher cipher, byte[] input) throws Exception {
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, output, 0);
        len += cipher.doFinal(output, len);
        byte[] result = new byte[len];
        System.arraycopy(output, 0, result, 0, len);
        return result;
    }

    // 测试用例
    public static void main(String[] args) throws Exception {
        // 生成密钥
        String key = generateKey();
        System.out.println("生成密钥: " + key);

        String plainText = "oOpPE5MG8ExsfZrIUUCc2gcMllR0";

        // 测试ECB模式
        String cipherTextEcb = encrypt(plainText, key, "ECB");
        System.out.println("ECB加密结果: " + cipherTextEcb);
        String decryptedEcb = decrypt(cipherTextEcb, key, "ECB");
        System.out.println("ECB解密结果: " + decryptedEcb);

        // 测试CBC模式
        String cipherTextCbc = encrypt(plainText, key);
        System.out.println("CBC加密结果: " + cipherTextCbc);
        String decryptedCbc = decrypt(cipherTextCbc, key);
        System.out.println("CBC解密结果: " + decryptedCbc);

        // 验证
        System.out.println("原始文本: " + plainText);
        System.out.println("解密验证: " + (plainText.equals(decryptedEcb) && plainText.equals(decryptedCbc)));
    }
}