package com.example.lambda.SocketMain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketMainTest {
    private static String reqMsg="";//此处添加对应请求报文

    public static void test() throws IOException {
        Socket socket=new Socket("10.250.9.193",9001);//此处填写报文发送的地址
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(reqMsg.getBytes("utf-8"));//输出报文
        byte[] bytes=new byte[1024];
        inputStream.read(bytes);//读入返回结果
        System.out.println(new String(bytes,"gbk"));//打印结果
    }
}
