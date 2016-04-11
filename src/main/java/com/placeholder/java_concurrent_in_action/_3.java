package com.placeholder.java_concurrent_in_action;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * http://blog.csdn.net/liuxuejiang158blog/article/details/22105455
 *
 * @author yuxiangque
 * @version 2016/4/11
 */
public class _3 {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    volatile int expectedId = 0;

    public static void main(String[] args) throws InterruptedException {
        new _3().test();
    }

    public void test() throws InterruptedException {
        Thread t1 = new Thread(new Count(0));
        Thread t2 = new Thread(new Count(1));
        Thread t3 = new Thread(new Count(2));
        Thread t4 = new Thread(new Count(3));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    public class Count implements Runnable {

        int id;
        int count;

        public Count(int id) {
            this.id = id;
            this.count = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; ++i) {
                try {
                    lock.lock();
                    while (expectedId != id) {
                        condition.await();
                    }
                    System.out.print(count + 1);
                    count = (count + 1) % 4;
                    if (expectedId == 0) {
                        expectedId = 1;
                    } else if (expectedId == 1) {
                        expectedId = 2;
                    } else if (expectedId == 2) {
                        expectedId = 3;
                    } else if (expectedId == 3) {
                        expectedId = 0;
                    }
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
