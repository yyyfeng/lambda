package com.example.lambda.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class Test {
	//RSA算法对文件加密
	public void encryptByRSA(String fileName, String saveFileName, String keyFileName) throws Exception {
		long start = System.currentTimeMillis();
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			String[] result = readKeyUtil(new File(keyFileName));
			RSAPublicKey key2 = getPublicKey(result[0], result[1]);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.WRAP_MODE, key2);
			byte[] wrappedKey = cipher.wrap(key);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(saveFileName));
			out.writeInt(wrappedKey.length);
			out.write(wrappedKey);
			InputStream in = new FileInputStream(fileName);
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			crypt(in, out, cipher);
			in.close();
			out.close();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("总共耗时：" + (end - start));
	}

	//RSA算法对文件解密
	public void decryptByRSA(String fileName, String saveFileName, String keyFileName) throws Exception {
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(fileName));
			int length = in.readInt();
			byte[] wrappedKey = new byte[length];
			in.read(wrappedKey, 0, length);
			String[] result = readKeyUtil(new File(keyFileName));
			RSAPrivateKey key2 = getPrivateKey(result[0], result[1]);

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.UNWRAP_MODE, key2);
			Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);

			OutputStream out = new FileOutputStream(saveFileName);
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);

			crypt(in, out, cipher);
			in.close();
			out.close();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//对数据块加密
	public void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException {
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];

		int inLength = 0;
		boolean next = true;
		while (next) {
			inLength = in.read(inBytes);
			System.out.println(inLength);
			if (inLength == blockSize) {
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			} else {
				next = false;
			}
		}
		if (inLength > 0) {
			outBytes = cipher.doFinal(inBytes, 0, inLength);
		} else {
			outBytes = cipher.doFinal();
		}
		out.write(outBytes);
	}

	//生成RSA密钥对
	public void generateRSAKey(String savePath) throws Exception {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			keygen.initialize(RSA_KEYSIZE, random);
			KeyPair keyPair = keygen.generateKeyPair();
			RSAPublicKey puk = (RSAPublicKey) keyPair.getPublic();
			createXmlFile(puk.getModulus().toString(), puk.getPublicExponent().toString(), savePath + "\\public.xml");
			RSAPrivateKey prk = (RSAPrivateKey) keyPair.getPrivate();
			createXmlFile(prk.getModulus().toString(), prk.getPrivateExponent().toString(), savePath + "\\private.xml");
		/*OutputStreamWriter osw  = new OutputStreamWriter(new FileOutputStream(savePath + "\\public.xml"));
		// 得到公钥字符串 
		System.out.println(puk.getModulus());
		System.out.println(puk.getPublicExponent());
		System.out.println(prk.getModulus());
		System.out.println(prk.getPrivateExponent());
        // 得到私钥字符串 
		osw.write(createXmlFile(puk.getModulus().toString(), puk.getPublicExponent().toString()));
		osw.close();
		osw = new OutputStreamWriter(new FileOutputStream(savePath + "\\private.xml"));
		osw.write(createXmlFile(prk.getModulus().toString(), prk.getPrivateExponent().toString()));
		osw.close();*/
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用模和指数生成RSA公钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA
	 * /None/NoPadding】
	 *
	 * @param modulus  模
	 * @param exponent 指数
	 * @return
	 */
	public static RSAPublicKey getPublicKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 使用模和指数生成RSA私钥
	 * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA
	 * /None/NoPadding】
	 *
	 * @param modulus  模
	 * @param exponent 指数
	 * @return
	 */
	public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {
		try {
			BigInteger b1 = new BigInteger(modulus);
			BigInteger b2 = new BigInteger(exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 生成xml字符串
	 *
	 * @return
	 * @throws Exception
	 */
	public static void createXmlFile(String Modulus, String Exponent, String filepath) throws Exception {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element supercarElement = root.addElement("Modulus");

		supercarElement.addText(Modulus);
		Element supercarElement2 = root.addElement("Exponent");

		supercarElement2.addText(Exponent);
		// 写入到一个新的文件中
		writer(document, filepath);
	}

	/**
	 * 把document对象写入新的文件
	 *
	 * @param document
	 * @throws Exception
	 */
	public static void writer(Document document, String path) throws Exception {
		// 紧凑的格式
		// 排版缩进的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("UTF-8");
		// 创建XMLWriter对象,指定了写出文件及编码格式
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream(new File(path)), "UTF-8"), format);
		// 写入
		writer.write(document);
		// 立即写入
		writer.flush();
		// 关闭操作
		writer.close();
	}

	public String[] readKeyUtil(File file) throws DocumentException {
		// 创建saxReader对象
		SAXReader reader = new SAXReader();
		// 通过read方法读取一个文件 转换成Document对象
		Document document = reader.read(file);
		//获取根节点元素对象
		Element node = document.getRootElement();
		Element ele = node.element("Modulus");
		String Modulus = ele.getTextTrim();
		Element ele1 = node.element("Exponent");
		String Exponent = ele1.getTextTrim();
		return new String[]{Modulus, Exponent};
	}

	private static final int RSA_KEYSIZE = 1024;

	public static void main(String[] args) throws Exception {
		//生成xml格式的公钥和私钥
//		new Test().generateRSAKey("D:/D_yingfeng/datatofile");
		//对文件进行加密
		new Test().encryptByRSA("D:/D_yingfeng/datatofile/test.txt", "D:/D_yingfeng/datatofile/123.txt", "D:/D_yingfeng/datatofile/allinfinance_server.cer");
		//对文件进行解密
//		new Test().decryptByRSA("D:/D_yingfeng/datatofile/123456.txt", "D:/D_yingfeng/datatofile/1234567.txt", "D:/D_yingfeng/datatofile/private.xml");

	}
}