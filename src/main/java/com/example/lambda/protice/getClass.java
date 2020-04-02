package com.example.lambda.protice;

import java.lang.reflect.Method;

/**
 * @Author: yingfeng
 * @Date: 2019/12/16
 */
class GetClass {

    public boolean getClazz() throws ClassNotFoundException {

        Class<?> aClass = Class.forName("com.example.lambda.pojo.CfsCardInfo");
        System.out.println("通过包名和类名获取对应的类："+aClass.toString());
        Method[] methods = aClass.getMethods();
        for (Method m:methods
             ) {
            System.out.println(m.getName());

        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        GetClass clazz = new GetClass();
        clazz.getClazz();
    }
}
