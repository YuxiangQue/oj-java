package com.placeholder.java_concurrent_in_action

import java.util.concurrent.CountDownLatch

/**
  * http://blog.csdn.net/liuxuejiang158blog/article/details/22300081
  *
  * @author yuxiangque
  * @version 2016/4/11
  */
object _6SynchronizeAtOnce extends App {

  val t1 = new Thread(new Runnable {
    override def run(): Unit = {
      println("this is thread1")
      if (flag == 2) {
        flag = 1
        stopMainSignal.countDown()
      } else {
        flag = 1
      }
      stopT2Signal.await()
      println("thread1 exit")
    }
  })
  val t2 = new Thread(new Runnable {
    override def run(): Unit = {
      println("this is thread2")
      obj.synchronized {
        if (flag == 1) {
          flag = 2
          stopMainSignal.countDown()
        } else {
          flag = 2
        }
      }
      stopT2Signal.countDown()
      println("thread2 exit")
    }
  })
  var stopT2Signal = new CountDownLatch(1)
  var stopMainSignal = new CountDownLatch(1)
  var obj = new Object()
  var flag = 0

  t1.start()
  t2.start()
  stopMainSignal.await()
}
