package com.example.lambda.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: TheBigBlue
 * @Description:
 * @Date: 2019/9/23
 */
public class RSAUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtils.class);

    /**
     * 加解密算法关键字
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥关键字
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥关键字
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 默认编码
     */
    public static final String CHARSET = "UTF-8";

    /**
     * @Author: TheBigBlue
     * @Description: 解密
     * @Date: 2019/9/23
     * @Param str: 加密的base64串
     * @Param privateKey: Base64私钥串
     * @Return: 明文
     **/
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(CHARSET));
        //base64编码的私钥
        byte[] keyBytes = Base64.decodeBase64(privateKey.getBytes(CHARSET));
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte), CHARSET);
    }

    /**
     * @Author: TheBigBlue
     * @Description: 加密
     * @Date: 2019/9/23
     * @Param data: 需要加密的数据
     * @Param publicKey: Base64公钥串
     * @Return:
     **/
    public static String encrypt(String data, String publicKey) throws Exception {
        // 取得公钥
        Key key = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey.getBytes(CHARSET))));
        // 对数据加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new String(Base64.encodeBase64(cipher.doFinal(data.getBytes(CHARSET))), CHARSET);
    }

    /**
     * 取得私钥。
     */
    public static String getPrivateKeyStr(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 取得公钥。
     */
    public static String getPublicKeyStr(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 初始化密钥。
     */
    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();    // 公钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  // 私钥
        Map<String, Object> keyMap = new HashMap<>(5);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * @Author: TheBigBlue
     * @Description: 生成公私钥，并保存至指定目录下
     * @Date: 2019/9/23
     * @Param filepath: 保存路径
     * @Return:
     **/
    /*public static void getKeyAndSave(String savePath) {
        try {
            Map<String, Object> keyMap = initKey();
            LOGGER.info("密钥保存路径为：{}", savePath);
            FileUtil.writeFile(getPrivateKeyStr(keyMap), savePath + "/private.store");
            FileUtil.writeFile(getPublicKeyStr(keyMap), savePath + "/public.store");
        } catch (Exception e) {
            LOGGER.error("生成密钥失败", e);
            throw new BusinessException("生成密钥失败", e);
        }
    }*/
}
