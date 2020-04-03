package com.example.lambda.MINASocket;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import java.io.IOException;
import java.net.InetSocketAddress;
 
public class Server {
    public static void main(String[] args) {
        //创建一个非阻塞的server端socket 用NIO
        SocketAcceptor acceptor = new NioSocketAcceptor();//创建接收数据的过滤器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
 
        //设定这个过滤器，将一行一行(/r/n)的读取数据
       chain.addLast("myChin",new ProtocolCodecFilter(new TextLineCodecFactory()));
 
        //设定服务器端的信息处理器
        acceptor.setHandler(new MinaServerHandler());
        //服务器端的端口号
        int port = 9999;
        try {
            //绑定端口，启动服务器(不会阻塞，立即返回)
            acceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Mina Server running,listenter on:" + port);
    }
}