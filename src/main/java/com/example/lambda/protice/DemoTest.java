package com.example.lambda.protice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: yingfeng
 * @Date: 2019/11/19
 */
public class DemoTest {

    private  final static Logger logger =LoggerFactory.getLogger(DemoTest.class);

    final  static String  XmlStr="<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
            "<soap:Header>"+
            "<ns2:interfaceVersion xmlns:ns2=\"http://www.unionpay.com/client/appprovider\" xmlns=\"http://www.chinatsm.com/\">00.00.00</ns2:interfaceVersion>"+
            "<ns2:transTimeSource xmlns:ns2=\"http://www.unionpay.com/client/appprovider\" xmlns=\"http://www.chinatsm.com/\">20150911172325</ns2:transTimeSource>"+
            "<ns2:transNoSource xmlns:ns2=\"http://www.unionpay.com/client/appprovider\" xmlns=\"http://www.chinatsm.com/\">772573</ns2:transNoSource>"+
            "<ns2:transType xmlns:ns2=\"http://www.unionpay.com/client/appprovider\" xmlns=\"http://www.chinatsm.com/\">2001</ns2:transType>"+
            "</soap:Header>"+
            "<soap:Body>"+
            "<mpanApplyRequest xmlns=\"http://www.chinatsm.com/\" xmlns:ns2=\"http://www.unionpay.com/client/appprovider\">"+
            "<seId>02E0000104001509080900001589FFFF</seId>"+
            "<seType>00110000</seType>"+
            "<seIssuer>01000011</seIssuer>"+
            "<applyChannel>01</applyChannel>"+
            "<instanceAid>A0000003330101020001050000001001</instanceAid>"+
            "<accountInfo>"+
            "<pan>6259667300000149</pan>"+
            "<expiryDate>0527</expiryDate>"+
            "<cvn2>791</cvn2>"+
            "</accountInfo>"+
            "<cardHolderInfo>"+
            "<msisdn>13888888888</msisdn>"+
            "</cardHolderInfo>"+
            "</mpanApplyRequest>"+
            "</soap:Body>"+
            "</soap:Envelope>";

    public static String getXmlStr(String msg) {
        int i = msg.lastIndexOf(">");
        logger.info("i ="+i);
        if (i == -1) {
            return null;
        } else {
            return msg.substring(0, i + 1);
        }
    }
    public static String getSignStr(String msg) {
        int i = msg.lastIndexOf(":");
        if (i == -1) {
            return null;
        } else {
            return msg.substring(i + 1, msg.length() - 1);
        }
    }



    public static void main(String[] args) {
        System.out.println("getXmlStr:"+getXmlStr(XmlStr));
        logger.info("--------------------");
        System.out.println("getSignStr:"+getSignStr(XmlStr));
    }

}
