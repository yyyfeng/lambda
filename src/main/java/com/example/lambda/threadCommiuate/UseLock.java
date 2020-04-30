package com.example.lambda.threadCommiuate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yingfeng
 * @desc 使用锁对象 实现线程通信
 */
public class UseLock {

    private static final Logger logger = LoggerFactory.getLogger(UseLock.class);

    //定义数组长度
    static final int LIST_SIZE=10;

    public static void main(String[] args) {

        Object lock=new Object();

        List<String> list=new ArrayList<>();

        ExecutorService pool = Executors.newCachedThreadPool();

        //创建线程A
        pool.execute(() -> {
            synchronized (lock){
                for (int i = 0; i < LIST_SIZE ; i++) {
                    list.add("111");
                    logger.info("实现线程A向向数组中添加一个元素，此时数组的长度为："+list.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (list.size()==5){
                        //唤醒B线程 但是并不释放锁 A线程执行完 才会到B线程
                        lock.notify();
                    }
                }
            }
        });

        pool.execute(() -> {
            while (true){
                synchronized (lock){
                    if (list.size()<LIST_SIZE){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    logger.info("线程B接收到A的结束通知，开始执行B的业务处理！");
                    break;
                }
            }
        });
    }


}
