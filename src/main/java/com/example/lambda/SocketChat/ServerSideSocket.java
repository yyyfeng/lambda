package com.example.lambda.SocketChat;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSideSocket implements Runnable {

    private Socket socket;
    private ArrayList<ServerSideSocket> pool;


    public ServerSideSocket(ArrayList<ServerSideSocket> pool, Socket socket) {
        this.pool = pool;
        this.socket = socket;
        this.pool.add(this);
        System.out.println("A client connected!");
    }


    @Override
    public void run() {
        receiveMessage();
    }

    /**
     * read data from ClientSideSocket.
     */
    private void receiveMessage() {
        try {
            byte[] bytes = new byte[1024];
            int n = 0;
            boolean isUserSendQuit = false;
            while (!isUserSendQuit) {
                while ((n = socket.getInputStream().read(bytes)) != -1) {
                    String str = new String(bytes, 0, n);
                    System.out.println("receivedMsg: " + str);
                    // broadcast this message to other client.  
                    for (ServerSideSocket otherSocket : pool) {
                        if (this != otherSocket) {
                            otherSocket.writeMessage(str);
                        }
                    }
                }
            }
        } catch (IOException e1) {
            System.out.println("receiveMessage:");
            System.out.println("A client disconnected!");
        } finally {
            disconnect();
        }
    }


    /**
     * write data to ClientSideSocket.
     *
     * @param message
     */
    public void writeMessage(String message) {
        try {
            socket.getOutputStream().write(message.getBytes());
            socket.getOutputStream().flush();
        } catch (IOException e1) {
            System.out.println("writeMessage:");
            System.out.println("A client disconnected!");
            disconnect();
        }
    }


    private void disconnect() {
        if (pool.remove(this)) {
            System.out.println("A client is removed successfully!");
            System.out.println("pool size: " + pool.size());
        } else {
            System.out.println("A client is not removed!");
        }
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}  