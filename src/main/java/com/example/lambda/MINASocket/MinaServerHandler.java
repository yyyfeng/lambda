package com.example.lambda.MINASocket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
 
/**
 * 服务器端的消息处理器
 */
 
public class MinaServerHandler extends IoHandlerAdapter {
    //一次会话被打开
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("welcome client" + session.getRemoteAddress());
    }
   //会话关闭
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("client closed");
    }
   //接收消息
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        //接收到的消息对象
        String msg = (String) message;
        System.out.println("收到客户端发来的消息:" + msg);
 
       //向客户端发送消息对象
        session.write(msg);
    }
}
