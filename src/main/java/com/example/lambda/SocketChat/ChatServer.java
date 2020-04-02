package com.example.lambda.SocketChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
  
public class ChatServer {  
      
    @SuppressWarnings("resource")  
    public static void main(String[] args) {  
          
        ArrayList<ServerSideSocket> pool = new ArrayList<>();
          
        try {  
              
            ServerSocket serverSocket = new ServerSocket(8002);
              
            System.out.println("Server starts up!");  
              
            while(true){  
                /** 
                 * ServerSocket.accept(); 
                 * 
                 Listens for a connection to be made to this socket and accepts it.  
                 The method blocks until a connection is made.  
 
                 A new Socket corresponding to the client socket is created, and this 
                 socket will be used for making communication with the client socket.  
                  
                 */  
                ServerSideSocket serverSideSocket =   
                    new ServerSideSocket(pool, serverSocket.accept());  
                Thread t = new Thread(serverSideSocket);  
                System.out.println("socket thread: " + t.getName() + " starts serving.");  
                t.start();  
            }  
              
              
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
    }  
  
}  