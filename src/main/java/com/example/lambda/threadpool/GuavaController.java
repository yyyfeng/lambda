package com.example.lambda.threadpool;

import com.example.lambda.pojo.User;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author whb
 */

@RestController
@RequestMapping(value = "/api/guava")
public class GuavaController {

    public static final ListeningExecutorService service =
            MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main(String[] args) throws InterruptedException {
        new GuavaController().testAsyn();
        service.shutdown();
    }


    public void testAsyn() throws InterruptedException {

        ListenableFuture<User>  booleanTask=null;

        long start = System.currentTimeMillis();
        User user=null;
        for (int i = 0; i <5; i++) {
            user=new User("user"+i,i,"男");
            booleanTask= service.submit(new testAsynchronous(user));

            Futures.addCallback(booleanTask, new FutureCallback<User>() {
                @Override
                public void onSuccess(@Nullable User user) {
                    System.out.println("回调函数返回结果user："+user);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Fail");
                }
            }, service);
            Thread.sleep(1000);
        }
        // 执行时间
        System.err.println("time: " + (System.currentTimeMillis() - start));
    }

    public class testAsynchronous implements Callable{
        private User user;

        public testAsynchronous(User user) {
            this.user = user;
        }

        @Override
        public User call() throws Exception {
            user=this.user;
            return user;
        }
    }

}