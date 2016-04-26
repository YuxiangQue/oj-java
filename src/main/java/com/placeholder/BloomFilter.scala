package com.placeholder

import scala.collection.mutable.ListBuffer

/**
  * @author yuxiangque
  * @version 2016/4/26
  */
abstract class BloomFilter[T]() {

    val bits = new scala.collection.mutable.BitSet()

    def hashs: ListBuffer[Hash[T]]

    def add(value: T): Unit = hashs.foreach(hash => {
        bits(hash.hash(value)) = true
    })

    def contains(value: T) = hashs.forall(hash => bits(hash.hash(value)))
}

trait Hash[T] {
    def hash(t: T): Int
}

class StringBloomFilter extends BloomFilter[String]() {
    val DefaultSize = 2 << 24
    val hashs1 = new ListBuffer[Hash[String]]()
    hashs1.appendAll(List(5, 7, 11, 13, 31, 37, 61).map((seed) => {
        new Hash[String] {
            val capacity = DefaultSize

            override def hash(t: String): Int = {
                t.foldLeft(0)((hash, ch) => {
                    seed * hash + ch
                }) & (capacity - 1)
            }
        }
    }))

    override def hashs: ListBuffer[Hash[String]] = hashs1
}

object StringBloomFilter {
    def main(args: Array[String]) {
        val sf = new StringBloomFilter
        val str = "yuxiangque@outlook.com"
        println(sf.contains(str) + "[false]")
        sf.add(str)
        println(sf.contains(str) + "[true]")
    }
}
