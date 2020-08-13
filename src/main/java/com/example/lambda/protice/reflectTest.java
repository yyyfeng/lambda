package com.example.lambda.protice;

import com.example.lambda.pojo.User;

import java.lang.reflect.Field;

public class reflectTest {

    private static Object getValue(Object object,String str) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(str);
        field.setAccessible(true);
        return  field.get(object);

    }


    public static String  getFieldValueByFieldName(String fieldName,Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //对private的属性的访问
            field.setAccessible(true);
            return (String ) field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user=new User("123",1,"男");
        Object name = getFieldValueByFieldName("name", user);
        System.out.println("name:"+name);

    }


}
