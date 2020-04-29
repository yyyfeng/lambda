package com.example.lambda.SocketChat;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.*;

@SuppressWarnings("resource")
public class ClientSideSocket {

    public static void main(String[] args) {
        try {

            final Socket socket = new Socket("127.0.0.1", 8002);
            final Scanner scanner = new Scanner(System.in);

            System.out.println("I am a client 1 !");

            final OutputStream outputStream = socket.getOutputStream();
            //发送消息
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = scanner.nextLine();
                            outputStream.write(str.getBytes());
                            outputStream.flush();
                        }
                    } catch (IOException e) {
                        System.out.println("Writing Quit.");
                        System.exit(0);
                    }
                }
            }).start();*/
            //接收消息

//            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 6000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            ExecutorService pool = Executors.newCachedThreadPool();
            pool.execute(() -> {
                try {
                    while (true) {
                        String str = scanner.nextLine();
                        outputStream.write(str.getBytes());
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Writing Quit.");
                    System.exit(0);
                }
            });

            pool.execute(() -> {
                try {
                    byte[] bytes = new byte[1024];
                    int n = 0;
                    while (true) {
                        while ((n = socket.getInputStream().read(bytes)) != -1) {
                            String str = new String(bytes, 0, n);
                                System.out.println(str);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading Quit.");
                    System.exit(0);
                }
            });

            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] bytes = new byte[1024];
                        int n = 0;
                        while (true) {
                            while ((n = socket.getInputStream().read(bytes)) != -1) {
                                String str = new String(bytes, 0, n);
//                                System.out.println(str);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Reading Quit.");
                        System.exit(0);
                    }
                }
            }).start();*/

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}