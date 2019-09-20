package com.example.lambda.protice;


import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

public class LambdaTest {

    public static void main(String[] args) {


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
        list.forEach((item)->{
            if(item.startsWith("j"))
                System.out.println(item);
        });

    }
}
