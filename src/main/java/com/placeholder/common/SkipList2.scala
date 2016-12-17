package com.placeholder.common

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

// http://www.cppblog.com/mysileng/archive/2013/04/06/199159.html
class SkipList2Test extends FlatSpec with Matchers {

  "skiplist" should "" in {
    val sl = new SkipList2
    val random = new Random()
    val total = 200
    for (i <- 1 to total) {
      sl.insert(i, i)
    }
    sl.length should be(200)
    for (i <- 100 to total) {
      sl.delete(i)
    }
    sl.length should be(99)
    sl.search(5) should be(5)
    sl.search(100) should be(-1)
  }
}

class SkipList2 {

  val random = new Random()
  val MaxLevel = 4
  val header: Node = new Node(MaxLevel, 0, 0)
  var length = 0
  var level = 1

  def insert(key: Int, value: Int): Unit = {
    val update = new Array[Node](MaxLevel)
    var x = header
    for (i <- level - 1 to 0 by -1) {
      while ((x.levels(i).forward != null) && x.levels(i).forward.key < key) {
        x = x.levels(i).forward
      }
      update(i) = x
    }

    val lvl = randomLevel()
    if (lvl > level) {
      for (i <- level until lvl) {
        update(i) = header
      }
      level = lvl
    }

    x = new Node(lvl, key, value)
    for (i <- 0 until lvl) {
      x.levels(i).forward = update(i).levels(i).forward
      update(i).levels(i).forward = x
    }
    length += 1
  }

  def randomLevel(): Int = {
    var lvl = 1
    while (random.nextDouble() < 0.5) {
      lvl += 1
    }
    if (lvl > MaxLevel) MaxLevel else lvl
  }

  def delete(key: Int): Unit = {
    val update = new Array[Node](MaxLevel)
    var x = header
    for (i <- level - 1 to 0 by -1) {
      while (x.levels(i).forward != null && x.levels(i).forward.key < key) {
        x = x.levels(i).forward
      }
      update(i) = x
    }
    x = x.levels(0).forward
    if (x != null && x.key == key) {
      for (i <- 0 until level) {
        if (update(i).levels(i).forward == x) {
          update(i).levels(i).forward = x.levels(i).forward
        }
      }
    }
    // 如果删除的是最大层的节点，那么需要重新维护跳表的
    while (level > 1 && header.levels(level - 1).forward == null) {
      level -= 1
    }
    length -= 1
  }

  def search(key: Int): Int = {
    var x = header
    for (i <- level - 1 to 0 by -1) {
      while (x.levels(i).forward != null && x.levels(i).forward.key < key) {
        x = x.levels(i).forward
      }
    }
    x = x.levels(0).forward
    if (x != null && x.key == key)
      x.value
    else
      -1
  }

  class Level() {
    var forward: Node = _
  }

  class Node(level: Int, val key: Int, val value: Int) {
    var levels = new Array[Level](level)
    for (i <- 0 until level) {
      levels(i) = new Level
    }
  }

}
