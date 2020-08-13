package com.example.lambda.threadpool;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yingfeng
 */

public class TestMyThreadPool {

    private static  final int  POOL_SIZE=2;

    private static final int TOTAL_TASK=5;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start  =System.currentTimeMillis();
        AtomicInteger count=new AtomicInteger(0);
//        创建线程池
//        MyThreadPool pool = new MyThreadPool(3,0);
/*        ThreadPoolExecutor pool=
                new ThreadPoolExecutor(3, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());*/
        //创建线程池 POOL_SIZE个线程
//        ExecutorService pool=Executors.newFixedThreadPool(POOL_SIZE);
        ExecutorService pool=Executors.newCachedThreadPool();
        //容器存放提交给线程池的任务
        /*BlockingQueue<Future<Integer>> queue=new LinkedBlockingQueue<Future<Integer>>();
        //添加任务
        for (int i = 0; i < TOTAL_TASK ; i++) {
            Future<Integer> future= (Future<Integer>) pool.submit(new MyTask("test-"+i));
            queue.add(future);
        }

        //线程池执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            Integer res = queue.take().get();
            System.out.println("res:"+res+"ms");
            count.addAndGet(res);
        }*/

        CompletionService<Integer> service=new ExecutorCompletionService<>(pool);
        //添加任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            service.submit(new TestThread());
        }

        //检查线程执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            Integer res = service.take().get();
            System.out.println("res:"+res+"ms");
            count.addAndGet(res);
        }


        /*System.out.println(pool);
        Thread.sleep(10000);
        //所有任务执行完成才destory
        pool.isShutdown();
        System.out.println(pool);*/
        //机器的cpu核心数
//        System.out.println(Runtime.getRuntime().availableProcessors());
        pool.shutdown();
 
    }


    static class TestThread implements Callable{



        @Override
        public Object call() throws Exception {
            Thread.sleep(1000);
            System.out.println("开始执行测试线程"+Thread.currentThread().getName());
            return null;
        }
    }

    /**
     * 任务类
     */
    static class MyTask implements Runnable{
        private String name;
        private Random r = new Random();
        public MyTask(String name){
            this.name = name;
        }
 
        public String getName(){
            return name;
        }
 
        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+" sleep InterruptedException:"
                        +Thread.currentThread().isInterrupted());
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("任务 " + name + " 完成");
            Thread.currentThread().interrupt();
        }
    }




 
}