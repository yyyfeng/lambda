package com.example.lambda.protice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: yingfeng
 * @Date: 2020/2/25
 */
public class testDate {
    public static void main(String[] args) throws ParseException {
        /*Date date = new Date();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date parse = simpleDateFormat.parse(format);
        System.out.println(parse);*/
        /*int v = (int)(Math.random() * 13);

        System.out.println(v);*/
        /*List res=new ArrayList();
        for(int i=0 ; i<100 ;i++){
            int ran=(int)(Math.random() * 13+1);
            if (!res.contains(ran)){
                res.add(ran);
            }
            if (res.size()>=6){
                break;
            }
        }
        System.out.println(res.get(0));
        for (Object m :res) {
            System.out.print(m+",");
        }*/
        System.out.println((int)(Math.floor(11/2)+1));
    }
}
