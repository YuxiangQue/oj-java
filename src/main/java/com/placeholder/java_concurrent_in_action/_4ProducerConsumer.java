package com.placeholder.java_concurrent_in_action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author yuxiangque
 * @version 2016/4/11
 */
public class _4ProducerConsumer {

    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(1024);
        Random random = new Random();
        ex.submit((Runnable) () -> {
            while (true) {
                try {
                    bq.put(random.nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ex.submit((Runnable) () -> {
            while (true) {
                try {
                    System.out.println(bq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
