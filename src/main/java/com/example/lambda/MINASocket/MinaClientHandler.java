package com.example.lambda.MINASocket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
 
public class MinaClientHandler extends IoHandlerAdapter {
    //当一个客户端连接进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("sessionOpened:" + session.getRemoteAddress());
        //向服务端发送消息对象
        session.write("我来了......");
    }
    //当一个客户端关闭时
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("sessionClosed");
    }
    //当客户端发送的消息到达时
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        //我们已经设定了服务器解析消息的规则是一行一行读取，这里就可以转为String
        String msg = (String) message;
        System.out.println("服务器发来的收到消息:" + msg);
    }

}