package com.example.lambda.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {
    
    public static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (Exception e) {
           e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

    public static void main(String[] args) {
        String md5Str = getMD5Str("6228213587566877421");
        System.out.println("MD5加密后"+md5Str);
    }

}