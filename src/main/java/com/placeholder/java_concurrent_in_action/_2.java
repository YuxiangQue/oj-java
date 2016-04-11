package com.placeholder.java_concurrent_in_action;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * http://blog.csdn.net/liuxuejiang158blog/article/details/22061267
 * 题目：编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按ABC的顺序显示；如：ABCABC….依次递推。 *
 *
 * @author yuxiangque
 * @version 2016/4/11
 */
public class _2 {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    volatile String expected = "A";

    public static void main(String[] args) throws InterruptedException {
        new _2().test();
    }

    public void test() throws InterruptedException {
        Thread t1 = new Thread(new Sayhello("A"));
        Thread t2 = new Thread(new Sayhello("B"));
        Thread t3 = new Thread(new Sayhello("C"));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    public class Sayhello implements Runnable {
        String me;

        public Sayhello(String me) {
            this.me = me;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    while (!Objects.equals(expected, me)) {
                        condition.await();
                    }
                    System.out.printf("%s", me);
                    if (Objects.equals(me, "A")) {
                        expected = "B";
                    } else if (Objects.equals(me, "B")) {
                        expected = "C";
                    } else if (Objects.equals(me, "C")) {
                        expected = "A";
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
