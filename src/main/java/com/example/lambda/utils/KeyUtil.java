package com.example.lambda.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;


/**
 * @program: acfp
 * @description: 获取公钥和私钥
 * @author: Lum Wang
 * @create: 2020-03-18 13:23
 */
public class KeyUtil {

//    private static void encryptByRSA(String fileName, String saveFileName, String keyFileName) throws Exception {
//        long start = System.currentTimeMillis();
//        try {
//            KeyGenerator keygen = KeyGenerator.getInstance("AES");
//            SecureRandom random = new SecureRandom();
//            keygen.init(random);
//            SecretKey key = keygen.generateKey();
//            RSAPublicKey key2 = (RSAPublicKey) KeyUtil.getPublicKeyFromCert("decCert/allinfinance_server.cer");
//            /*String[] result = readKeyUtil(new File(keyFileName));
//            RSAPublicKey key2 = getPublicKey(result[0], result[1]);*/
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.WRAP_MODE, key2);
//            byte[] wrappedKey = cipher.wrap(key);
//            DataOutputStream out = new DataOutputStream(new FileOutputStream(saveFileName));
//            out.writeInt(wrappedKey.length);
//            out.write(wrappedKey);
//            InputStream in = new FileInputStream(fileName);
//            cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            crypt(in, out, cipher);
//            in.close();
//            out.close();
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("总共耗时：" + (end - start));
//    }



//    //对数据块加密
//    private static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException {
//        int blockSize = cipher.getBlockSize();
//        int outputSize = cipher.getOutputSize(blockSize);
//        byte[] inBytes = new byte[blockSize];
//        byte[] outBytes = new byte[outputSize];
//
//        int inLength = 0;
//        boolean next = true;
//        while (next) {
//            inLength = in.read(inBytes);
//            System.out.println(inLength);
//            if (inLength == blockSize) {
//                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
//                out.write(outBytes, 0, outLength);
//            } else {
//                next = false;
//            }
//        }
//        if (inLength > 0) {
//            outBytes = cipher.doFinal(inBytes, 0, inLength);
//        } else {
//            outBytes = cipher.doFinal();
//        }
//        out.write(outBytes);
//    }

    /**
     * 读取证书中的公钥
     *
     * @param certLocation
     * @return
     * @throws CertificateException
     */
    private static Logger logger = LoggerFactory.getLogger(KeyUtil.class);
    public static final String SIGN_ALGORITHM_SHA256RSA = "SHA256withRSA";

    public static PublicKey getPublicKeyFromCert(String certLocation) throws CertificateException, IOException {

        try (InputStream inputStream = new FileInputStream(ResourceUtils.getFile(certLocation))) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            PublicKey publicKey = cert.getPublicKey();
            return publicKey;
        } catch (Exception e) {
            logger.error("读取证书失败");
            throw e;
        }
    }


    /**
     * 从keystore获取公钥
     *
     * @param keyStoreLocation
     * @param password
     * @return
     */
    public static PublicKey getPublicKeyFromKeyStore(String keyStoreLocation, String password, String alias) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        try (InputStream inputStream = new FileInputStream(ResourceUtils.getFile(keyStoreLocation))) {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] nPassword = password.toCharArray();
            ks.load(inputStream, nPassword);

            // 获取和别名绑定的证书
            java.security.cert.Certificate cert = ks.getCertificate(alias);
            // 从证书中获取公钥
            return cert.getPublicKey();

        } catch (Exception e) {
            logger.error("获取公钥时读取keystore失败");
            throw e;
        }
    }


    /**
     * 从KeyStore获取私钥，需要密码
     *
     * @param keyStoreLocation
     * @param password
     * @return
     */
    public static PrivateKey getPrivateKeyFromKeyStore(String keyStoreLocation, String password, String alias)
            throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {

        try (InputStream inputStream = new FileInputStream(ResourceUtils.getFile(keyStoreLocation))) {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] nPassword = password.toCharArray();
            ks.load(inputStream, nPassword);
            // 获取和别名绑定的密钥，并用给定密码来恢复它
            return (PrivateKey) ks.getKey(alias, nPassword);
        } catch (Exception e) {
            logger.error("获取私钥时读取keystore失败");
            throw e;
        }
    }

    /*public static void main(String[] args) throws CertificateException, IOException {
        PublicKey publicKeyFromCert = getPublicKeyFromCert("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\allinfinance_server.cer");
        encrypt(publicKeyFromCert, "D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\logback.xml", "D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\logback.xml.enc");

    }*/

    public static void main(String[] args) throws Exception {
//        PrivateKey privateKey = getPrivateKeyFromKeyStore("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\serverKeystore.p12", "123456", "serverkey");
//        decrypt("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\logback.xml.enc", privateKey,"D:\\D_yingfeng\\datatofile\\logback.xml");

        PublicKey publicKeyFromCert = getPublicKeyFromCert("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\pub_key_dev.asc");
//        PublicKey publicKeyFromCert = getPublicKeyFromCert("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\allinfinance_server.cer");
        Cipher cipher = Cipher.getInstance("RSA");
//        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKeyFromCert);

        String srcStr = "11111111";
        byte[] buf = srcStr.getBytes();
        byte[] enStr = cipher.doFinal(buf);
        logger.info("enStr:" + enStr.length + "|" + buf.length);

        PrivateKey privateKey = getPrivateKeyFromKeyStore("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\serverKeystore.p12", "123456", "serverkey");
        Cipher cipher2 = Cipher.getInstance("RSA");
//        Cipher cipher2 = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher2.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] deStr = cipher2.doFinal(enStr);
        logger.info("deStr:" + deStr.length + new String(deStr));

    }

    public static void encrypt(PublicKey publicKey, String filePath, String dstFile) {
        FileInputStream fis = null;
        FileOutputStream fileOutputStream = null;

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            fileOutputStream = new FileOutputStream(dstFile);

            fis = new FileInputStream(filePath);
            byte[] buf = new byte[64];
            while (fis.read(buf) >= 0) {
//                fileOutputStream.write(Base64.getEncoder().encode(cipher.doFinal(buf)));
                byte[] enStr = cipher.doFinal(buf);
                logger.info("enStr:" + enStr.length);
                PrivateKey privateKey = getPrivateKeyFromKeyStore("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\serverKeystore.p12", "123456", "serverkey");
                Cipher cipher2 = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] deStr = cipher2.doFinal(enStr);
                logger.info("deStr:" + deStr.length + new String(deStr));
            }
        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 用私钥解密密文
     *
     * @param srcFile
     * @param privateKey
     * @return
     */
    public static String decrypt(String srcFile, PrivateKey privateKey,String dstFile) throws Exception {

        FileInputStream fis = new FileInputStream(srcFile);
        StringBuffer sb = null;
        FileOutputStream fileOutputStream=new FileOutputStream(dstFile);


        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            sb = new StringBuffer();

            byte[] buf = new byte[64];
            while (fis.read(buf) >= 0) {
//                sb.append(new String(Base64.getDecoder().decode(cipher.doFinal(buf))));
                fileOutputStream.write(cipher.doFinal(Base64.getEncoder().encode(buf)));
            }
//            return new String(cipher.doFinal(Base64.getDecoder().decode("abcd")));
        } catch (Exception e) {
            logger.error("解密失败！", e);
        }
        return sb.toString();

    }


    /**
     * RSA Sha256摘要  私钥签名
     *
     * @param privateKey 私钥
     * @param data       消息
     * @return 签名
     */
    public static byte[] signWithSha256(RSAPrivateKey privateKey, byte[] data) {
        byte[] result = null;
        Signature st;
        try {
            st = Signature.getInstance(SIGN_ALGORITHM_SHA256RSA);
            st.initSign(privateKey);
            st.update(data);
            result = st.sign();
        } catch (Exception e) {
            logger.error("Fail: RSA with sha256 sign", e);
        }
        return result;
    }

}
