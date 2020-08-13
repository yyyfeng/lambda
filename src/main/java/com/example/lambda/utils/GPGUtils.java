package com.example.lambda.utils;

import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.bc.*;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;



public class GPGUtils {


    public static void main(String[] args) throws IOException, PGPException, NoSuchProviderException {
        PGPPublicKey pgpPublicKey = readPublicKeyFromCol(new FileInputStream(new File("D:\\D_yingfeng\\idea_workspace\\lambda\\src\\main\\resources\\tmp\\aif_pub_key.pem")));
        System.out.println(pgpPublicKey);

        encryptFile(new FileOutputStream(new File("D:\\D_yingfeng\\datatofile\\20200729.GPG")),"D:\\D_yingfeng\\datatofile\\1234567.txt",pgpPublicKey);


    }

    /**
     *
     * @param in
     * @param keyId
     * @return
     * @throws IOException
     * @throws PGPException
     */

    public static PGPSecretKey readSecretKeyFromCol(InputStream in, long keyId) throws IOException, PGPException {
        in = PGPUtil.getDecoderStream(in);
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(in,new BcKeyFingerprintCalculator());

        PGPSecretKey key = pgpSec.getSecretKey(keyId);

        if (key == null) {
            throw new IllegalArgumentException("Can't find encryption key in key ring.");
        }
        return key;
    }

    /**
     * 读取公钥
     * @param in
     * @return PGPPublicKey
     * @throws IOException
     * @throws PGPException
     */
    @SuppressWarnings("rawtypes")
    public static PGPPublicKey readPublicKeyFromCol(InputStream in) throws IOException,PGPException {
        in = PGPUtil.getDecoderStream(in);
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(in,new BcKeyFingerprintCalculator());
        PGPPublicKey key = null;
        Iterator rIt = pgpPub.getKeyRings();
        while (key == null && rIt.hasNext()) {
            PGPPublicKeyRing kRing = (PGPPublicKeyRing) rIt.next();
            Iterator kIt = kRing.getPublicKeys();
            while (key == null && kIt.hasNext()) {
                PGPPublicKey k = (PGPPublicKey) kIt.next();
                if (k.isEncryptionKey()) {
                    key = k;
                }
            }
        }
        if (key == null) {
            throw new IllegalArgumentException("Can't find encryption key in key ring.");
        }
        return key;
    }


    /**
     * 解密文件
     * @param in
     * @param secKeyIn
     * @param pubKeyIn
     * @param pass
     * @throws IOException
     * @throws PGPException
     * @throws InvalidCipherTextException
     */
    public static void decryptFile(InputStream in,InputStream secKeyIn,InputStream pubKeyIn,char[] pass) throws IOException,PGPException, InvalidCipherTextException {
        Security.addProvider(new BouncyCastleProvider());

        PGPPublicKey pubKey = readPublicKeyFromCol(pubKeyIn);

        PGPSecretKey secKey = readSecretKeyFromCol(secKeyIn,pubKey.getKeyID());

        in = PGPUtil.getDecoderStream(in);

        JcaPGPObjectFactory pgpFact;


        PGPObjectFactory pgpF = new PGPObjectFactory(in,new BcKeyFingerprintCalculator());

        Object o = pgpF.nextObject();
        PGPEncryptedDataList encList;

        if (o instanceof PGPEncryptedDataList) {

            encList = (PGPEncryptedDataList) o;

        } else {

            encList = (PGPEncryptedDataList) pgpF.nextObject();

        }

        Iterator<PGPPublicKeyEncryptedData> itt = encList.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData encP = null;
        while (sKey == null && itt.hasNext()) {
            encP = itt.next();
            secKey = readSecretKeyFromCol(new FileInputStream("PrivateKey.asc"),encP.getKeyID());
            sKey = secKey.extractPrivateKey(new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pass));
        }
        if (sKey == null) {
            throw new IllegalArgumentException("Secret key for message not found.");
        }

        InputStream clear = encP.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));

        pgpFact = new JcaPGPObjectFactory(clear);

        PGPCompressedData c1 = (PGPCompressedData) pgpFact.nextObject();

        pgpFact = new JcaPGPObjectFactory(c1.getDataStream());

        PGPLiteralData ld = (PGPLiteralData) pgpFact.nextObject();
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        InputStream inLd = ld.getDataStream();

        int ch;
        while ((ch = inLd.read()) >= 0) {
            bOut.write(ch);
        }

        //System.out.println(bOut.toString());

        bOut.writeTo(new FileOutputStream(ld.getFileName()));
        //return bOut;

    }


    /**
     * 加密文件
     * @param out
     * @param fileName
     * @param encKey
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws PGPException
     */
    public static void encryptFile(OutputStream out,String fileName,PGPPublicKey encKey) throws IOException, NoSuchProviderException,PGPException {
        Security.addProvider(new BouncyCastleProvider());

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);

        PGPUtil.writeFileToLiteralData(comData.open(bOut),PGPLiteralData.BINARY,new File(fileName));

        comData.close();

        PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(new BcPGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.TRIPLE_DES).setSecureRandom(new SecureRandom()));

        cPk.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(encKey));

        byte[] bytes = bOut.toByteArray();

        OutputStream cOut = cPk.open(out,bytes.length);

        cOut.write(bytes);

        cOut.close();

        out.close();
    }


}
