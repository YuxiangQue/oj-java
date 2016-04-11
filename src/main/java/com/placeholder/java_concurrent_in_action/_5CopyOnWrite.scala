package com.placeholder.java_concurrent_in_action

import java.util
import java.util.Random
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
  * @author yuxiangque
  * @version 2016/4/11
  */
object _5CopyOnWrite extends App {
  val lock = new ReentrantReadWriteLock()
  val read = lock.readLock()
  val write = lock.writeLock()
  val file = new util.ArrayList[Integer]()
  val ex = Executors.newCachedThreadPool()
  var random = new Random()

  class Writer extends Runnable {
    override def run(): Unit = {
      while (true) {
        write.lock()
        file.add(random.nextInt())
        write.unlock()
        Thread.sleep(100)
      }
    }
  }

  class Reader extends Runnable {
    override def run(): Unit = {
      while (true) {
        read.lock()
        val index = file.size() - 1
        println(s"$index: ${file.get(index)}")
        read.unlock()
      }
    }
  }

  ex.submit(new Writer())
  (1 to 10).foreach((i) => {
    ex.submit(new Reader())
  })
}
