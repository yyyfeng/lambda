package com.example.lambda.utils;

import com.example.lambda.pojo.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class writeDateToFile {

    /*public static void FileString3(String path, String data) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");// 设置编码格式
            writer.write(data);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/



    public static void FileString4(String path, String data) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path, true);// 追加写入
            outputStream.write(("\r\n" + data).getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void FileString3(String path, List<User> users) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");// 设置编码格式
            for (User u: users) {
                writer.write(String.valueOf(u));
                writer.write("\r\n");
            }
            writer.write("结束！");
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        String path="D:/D_yingfeng/datatofile/test.txt";
        User user1=new User("111",1,"1");
        User user2=new User("222",1,"1");
        User user3=new User("333",1,"1");
        List<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        FileString3(path,users);
    }

}
