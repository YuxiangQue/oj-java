package com.placeholder.java_concurrent_in_action;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * http://blog.csdn.net/liuxuejiang158blog/article/details/21977009
 * 题目：子线程循环 10 次，接着主线程循环 100 次，接着又回到子线程循环 10 次，接着再回到主线程又循环 100 次，如此循环50次，试写出代码。
 *
 * @author yuxiangque
 * @version 2016/4/11
 */
public class _1 {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    volatile int currentNum = 10;

    public static void main(String[] args) throws InterruptedException {
        new _1().test();
    }

    public void test() throws InterruptedException {
        Thread t1 = new Thread(new CountRunnable(10));
        Thread t2 = new Thread(new CountRunnable(100));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public class CountRunnable implements Runnable {
        int me;

        public CountRunnable(int me) {
            this.me = me;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                try {
                    lock.lock();
                    while (currentNum != me) {
                        condition.await();
                    }
                    for (int j = 1; j <= me; j++) {
                        System.out.printf("%d ", j);
                    }
                    System.out.println();
                    if (me == 10) {
                        currentNum = 100;
                    } else {
                        currentNum = 10;
                    }
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
