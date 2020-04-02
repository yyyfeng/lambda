package com.example.lambda.protice;


import com.example.lambda.pojo.CfsCardInfo;
import com.example.lambda.pojo.CfsMailAddressFlagEnum;
import com.example.lambda.pojo.TestDto;
import org.springframework.cglib.beans.BeanMap;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

public class LambdaTest {

    public static void main(String[] args) throws UnsupportedEncodingException {


        List<String> list=Arrays.asList("java","C++","lambda","html");
        //遍历
        //list.stream().forEach(System.out::println);

        //筛选
        //Predicate<String> contain=item->item.contains("j");
        //list.stream().filter(contain).forEach(System.out::println);

        //连两个条件且关系的筛选
//        Predicate<String> contain1=item->item.contains("j");
//        Predicate<String> contain2=item->item.contains("a");
//        list.stream().filter(contain1.and(contain2)).forEach(System.out::println);

        //两个条件或关系的筛选
//        Predicate<String> contain1=item->item.contains("j");
//        Predicate<String> contain2=item->item.contains("a");
//        list.stream().filter(contain1.or(contain2)).forEach(System.out::println);

        //追加字符
//        list.stream().map(item->item+String.valueOf(1)).forEach(System.out::println);
     /*   list.forEach((item)->{
            if(item.startsWith("j"))
                System.out.println(item);
        });*//**/
        System.out.println("---------------------");
        System.out.println(String.format("%010d",5555));
        System.out.println(String.format("%010d",5555).length());
        System.out.println("123456789".substring(5));
        System.out.println("123456".replaceAll("3","---"));

//        BeanMap map =BeanMap.create(new CfsCardInfo());
//        CfsCardInfo cfsCardInfo=new CfsCardInfo();
//        cfsCardInfo.setMailAddressFlag("1");
//        System.out.println(map.keySet());
//        for ( Object o :map.keySet()) {
////            System.out.println(o);
//            CfsMailAddressFlagEnum cfsMailAddressFlagEnum = CfsMailAddressFlagEnum.valueByCode(cfsCardInfo.getMailAddressFlag());
////            System.out.println(cfsMailAddressFlagEnum);
//        }
//        String smsValue="您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！" +
//                "您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！"
//        +"您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！"
//        +"您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！"
//        +"您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！"
//        +"您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！"
//        +"您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！"
//        +"您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！您的美团信用卡已经寄送至您的公司地址，请注意查收！";
//        byte[] smsBytes = smsValue.getBytes("GBK");
//        if (smsBytes.length > 512) {
//            byte[] bytes = new byte[512];
//            System.arraycopy(smsBytes, 0, bytes, 0, 512);
//            smsValue = new String(bytes, "GBK");
//            System.out.println("1--"+smsValue.length()+smsValue);
//        } else if (smsBytes.length < 512) {
//            String lenStr = "%-" + (512 - smsValue.getBytes("GBK").length) + "s";
//            smsValue = smsValue + String.format(lenStr, "");
//            System.out.println("2-"+smsValue.length()+smsValue);
//        }
    }
}
