package com.example.lambda.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yingfeng
 */
public class TestLogBack {

    private  static  final Logger logger= LoggerFactory.getLogger(TestLogBack.class);

    public static void testLog(){
        String mobileNo="18815618608";
        logger.info("mobileNo:{}",mobileNo);

        String idNo="342201199906095678";
        logger.info("idNo:{}",idNo);

        SensitiveConverter sensitiveConverter=new SensitiveConverter();
        String decryptMobileNo = sensitiveConverter.decrypt(mobileNo, 3, 4);
        System.out.println("解密后手机号："+decryptMobileNo);
        String decryptIdNo = sensitiveConverter.decrypt(idNo, 4, 4);
        System.out.println("解密后身份证号："+decryptIdNo);
    }

    public static void main(String[] args) {
        testLog();

    }
}
