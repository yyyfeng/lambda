package com.example.lambda.protice;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;



public class ThreadDemo  extends Thread{

    public static void main(String[] args) {
        Thread thread=new Thread(new MyTread());
        thread.start();
        /*String name = thread.getName();
        System.out.println("thread.getName"+name);*/

        new Thread(new A()).start();

//        new Thread(new B()).start();

        FutureTask<Integer> future=new FutureTask<>(new B());
        Thread thread1=new Thread(future);
        thread1.start();


        //获取线程数
        ThreadGroup threadGroup=Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent()!=null){
            threadGroup=threadGroup.getParent();
            System.out.println(threadGroup.getName());
        }
        threadGroup.list();
        System.out.println("当前线程数："+threadGroup.activeCount());

    }


    static class MyTread implements Runnable{
        @Override
        public void run() {
            System.out.println("实现Runnable接口！");
        }
    }

    static class A extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("继承Thread");
        }
    }

    static class B implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("实现Callable");
            return 1024;
        }
    }
}
