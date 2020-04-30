package com.example.lambda.threadCommiuate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yingfeng
 * @Desc  volatile关键字  通过共享变量实现线程通信
 */
public class SharedVariable {

    private static final Logger logger= LoggerFactory.getLogger(SharedVariable.class);

    static volatile boolean notice=false;

    //定义数组长度
    static final int LIST_SIZE=10;

    public static void main(String[] args) {

        List<String> list=new ArrayList<>();

        ExecutorService pool = Executors.newCachedThreadPool();

        //创建线程A
        pool.execute(() -> {
            for (int i = 0; i < LIST_SIZE ; i++) {
                list.add("111");
                logger.info("实现线程A向向数组中添加一个元素，此时数组的长度为："+list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size()==5){
                    notice=true;
                }
            }
        });

        pool.execute(() -> {
            while (true){
                if (notice){
                    logger.info("线程B接收到A的结束通知，开始执行B的业务处理！");
                    if (list.size()<LIST_SIZE){
                        list.add("222");
                        logger.info("B向数组中添加元素，此时数组的长度为：{}",list.size());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
        });
    }
}
