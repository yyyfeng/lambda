package com.example.lambda.threadCommiuate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yingfeng
 * @desc 使用JUC 工具类 CountDownLatch
 */
public class ShareState {
    private static final Logger logger = LoggerFactory.getLogger(ShareState.class);

    //定义数组长度
    static final int LIST_SIZE=10;

    public static void main(String[] args) {

        CountDownLatch count=new CountDownLatch(1);

        List<String> list=new ArrayList<>();

        ExecutorService pool = Executors.newCachedThreadPool();

//        ThreadPoolExecutor pool =
//                new ThreadPoolExecutor(3,3,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());

        //创建线程A
        pool.execute(() -> {
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
                        count.countDown();
                    }
                }
        });

        pool.execute(() -> {
            while (true){
                    if (list.size()<LIST_SIZE){
                        try {
                            count.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    logger.info("线程B接收到A的结束通知，开始执行B的业务处理！");
                    break;
                }
        });
    }

}
