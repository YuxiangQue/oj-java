package com.placeholder.hihoCoder.weekly

import java.util.Scanner

import com.placeholder.common.trie.Trie

/**
  * @author yuxiangque
  * @version 2016/4/26
  */
object _2TrieTree {
    def main(args: Array[String]) {
        val trie = new Trie()
        val scanner = new Scanner(System.in)
        val n = scanner.nextInt()
        (0 until n).foreach((index) => {
            trie.insert(scanner.next())
        })
        val m = scanner.nextInt()
        (0 until m).foreach((index) => {
            println(trie.countStartsWith(scanner.next()))
        })
    }
}
