package com.example.lambda.MINASocket;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import java.net.InetSocketAddress;
import java.util.Scanner;
 
public class Client {
    public static void main(String[] args) {
       //创建连接
        NioSocketConnector connector = new NioSocketConnector();
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
 
        //设定这个过滤器，将一行一行(/r/n)的读取数据
        chain.addLast("myChin",new ProtocolCodecFilter(new TextLineCodecFactory()));
 
       //设定客户端的信息处理器
        connector.setHandler(new MinaClientHandler());
        connector.setConnectTimeoutMillis(10000);
 
       //连接服务器
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 9999));
 
        //等待连接成功
        cf.awaitUninterruptibly();
        Scanner input = new Scanner(System.in);
 
        while (true){
            System.out.println("请输入:");
            String info = input.nextLine();
 
            //发送消息
            cf.getSession().write(info);
         }
    }
}