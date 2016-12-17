package com.placeholder.common

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

// https://github.com/xjdrew/lua-zset
class SkipListTest extends FlatSpec with Matchers {
  "skiplist" should "" in {
    val sl = new SkipList[String]()
    val random = new Random()
    val total = 200
    for (i <- 1 to total) {
      sl.insert(i, i.toString)
      sl.insert(i, i.toString)
    }
    sl.length should be(400)
    for (i <- 1 to total) {
      sl.delete(i, i.toString)
    }
    sl.length should be(200)
    sl.dump()
    sl.firstInRange(20.5, 27.5).score should be(21)
    sl.lastInRange(20.5, 27.5).score should be(27)
    sl.getNodeByRank(20).score should be(20)
    sl.getRank(5, "5") should be(5)
  }
}

class SkipList[T >: Null](implicit ordering: Ordering[T]) {

  val SkipListMaxLevel = 4
  val SkipListP = 0.5
  private val random = new Random()
  var header: SkipListNode = _
  var tail: SkipListNode = _
  // 节点数量
  var length: Int = 0
  // 目前表内节点的最大层数
  var level: Int = 1

  def dump(): Unit = {
    var i = 0
    var x = header
    while (x.levels(0).forward != null) {
      x = x.levels(0).forward
      i += 1
      print(s"node ${i}: score:${x.score}, member:${x.member} levels:${x.levels.length} \n")
    }
  }

  header = new SkipListNode(SkipListMaxLevel, 0, null)

  def dumpRankRange(min: Double, max: Double): Unit = {
    printf(s"rank range: $min $max")
  }

  // 最坏 O(N) 平均
  def insert(score: Int, member: T): Unit = {

    val update = new Array[SkipListNode](SkipListMaxLevel)
    val rank = new Array[Int](SkipListMaxLevel)
    var x = header
    for (i <- level - 1 to 0 by -1) {
      rank(i) = if (i == level - 1) 0 else rank(i + 1)
      while (x.levels(i).forward != null &&
        (x.levels(i).forward.score < score ||
          x.levels(i).forward.score == score && ordering.lt(x.levels(i).forward.member, member))) {
        rank(i) += x.levels(i).span
        x = x.levels(i).forward
      }
      update(i) = x
    }

    val lvl = randomLevel()
    if (lvl > this.level) {
      for (i <- this.level until lvl) {
        rank(i) = 0
        update(i) = header
        update(i).levels(i).span = length
      }
      this.level = lvl
    }

    x = new SkipListNode(lvl, score, member)
    for (i <- 0 until lvl) {
      x.levels(i).forward = update(i).levels(i).forward
      update(i).levels(i).forward = x

      // update span covered by update[i] as x is inserted here
      x.levels(i).span = update(i).levels(i).span - (rank(0) - rank(i))
      update(i).levels(i).span = (rank(0) - rank(i)) + 1
    }

    // increment span for untouched levels
    for (i <- lvl until this.level) {
      update(i).levels(i).span += 1
    }

    x.backward = if (update(0) == header) null else update(0)

    if (x.levels(0).forward != null)
      x.levels(0).forward.backward = x
    else
      tail = x
    length += 1
  }

  private def randomLevel(): Int = {
    var level = 1
    while (random.nextDouble() < SkipListP) {
      level += 1
    }
    if (level < SkipListMaxLevel) level else SkipListMaxLevel
  }

  def delete(score: Int, member: T): Boolean = {
    val update = new Array[SkipListNode](SkipListMaxLevel)
    val rank = new Array[Int](SkipListMaxLevel)
    var x = header
    for (i <- level - 1 to 0 by -1) {
      rank(i) = if (i == level - 1) 0 else rank(i + 1)
      while (x.levels(i).forward != null &&
        (x.levels(i).forward.score < score ||
          x.levels(i).forward.score == score && ordering.lt(x.levels(i).forward.member, member))) {
        rank(i) += x.levels(i).span
        x = x.levels(i).forward
      }
      update(i) = x
    }

    /* We may have multiple elements with the same score, what we need
     * is to find the element with both the right score and object. */
    x = x.levels(0).forward
    if (x != null && score == x.score && x.member == member) {
      deleteNode(x, update)
      true
    } else {
      false
    }
  }

  private def deleteNode(x: SkipListNode, update: Array[SkipListNode]) = {
    for (i <- 0 until level) {
      if (update(i).levels(i).forward.eq(x)) {
        update(i).levels(i).span += x.levels(i).span - 1
        update(i).levels(i).forward = x.levels(i).forward
      } else {
        update(i).levels(i).span -= 1
      }
    }
    if (x.levels(0).forward != null) {
      x.levels(0).forward.backward = x.backward
    } else {
      tail = x.backward
    }
    while (level > 1 && header.levels(level - 1).forward == null) {
      level -= 1
    }
    length -= 1
  }

  // 找到跳跃表中第一个符合给定范围的元素
  def firstInRange(min: Double, max: Double): SkipListNode = {
    // If everything is out of range, return early.
    if (!isInRange(min, max)) null
    else {
      var x = header
      for (i <- level - 1 to 0 by -1) {
        // Go forward while *OUT* of range.
        while (x.levels(i).forward != null && x.levels(i).forward.score < min)
          x = x.levels(i).forward
      }
      // This is an inner range, so the next node cannot be NULL.
      x = x.levels(0).forward
      x
    }
  }

  private def isInRange(min: Double, max: Double): Boolean = {
    if (min > max) false
    else {
      var x = tail
      if (x == null || x.score < min)
        return false
      x = header.levels(0).forward
      if (x == null || x.score > max)
        return false
      true
    }
  }

  def lastInRange(min: Double, max: Double): SkipListNode = {
    // If everything is out of range, return early.
    if (!isInRange(min, max)) null
    else {
      var x = header
      for (i <- level - 1 to 0 by -1) {
        /* Go forward while *IN* range. */
        while (x.levels(i).forward != null && x.levels(i).forward.score <= max)
          x = x.levels(i).forward
      }
      x
    }
  }

  def deleteRangeByScore(score: Int) = ???

  def deleteRangeByRank(rank: Int) = ???

  def getRank(score: Double, member: T): Long = {
    var x = header
    var rank = 0L
    for (i <- level - 1 to 0 by -1) {
      while (x.levels(i).forward != null &&
        (x.levels(i).forward.score < score ||
          x.levels(i).forward.score == score && ordering.lteq(x.levels(i).forward.member, member))) {
        rank += x.levels(i).span
        x = x.levels(i).forward
      }
      if (x.member != null && x.member == member) {
        return rank
      }
    }
    0
  }

  def getNodeByRank(rank: Long): SkipListNode = {
    if (rank == 0 || rank > length) null
    else {
      var traversed = 0L
      var x = header
      for (i <- level - 1 to 0 by -1) {
        while (x.levels(i).forward != null &&
          (traversed + x.levels(i).span) <= rank) {
          traversed += x.levels(i).span
          x = x.levels(i).forward
        }
        if (traversed == rank) {
          return x
        }
      }
      null
    }
  }

  class SkipListLevel {
    // 前进指针
    var forward: SkipListNode = _
    // 这个层跨越的节点数量
    var span = 0

  }

  class SkipListNode(level: Int, var score: Double, var member: T) {

    // 层
    val levels: Array[SkipListLevel] = new Array[SkipListLevel](level)
    // 后退指针
    var backward: SkipListNode = _

    def forward: SkipListNode = levels(0).forward

    (0 until level).foreach(i => {
      levels(i) = new SkipListLevel
    })
  }

}
