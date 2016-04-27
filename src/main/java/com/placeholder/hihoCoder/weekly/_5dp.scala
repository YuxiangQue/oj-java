package com.placeholder.hihoCoder.weekly

import java.util.Scanner


/**
  * http://hihocoder.com/contest/hiho5/problem/1
  * 动态规划
  *
  */
object _5dp {

    def main(args: Array[String]) {
        // dp[i][j] = rewards[i][j] + max(dp[i-1][j-1], dp[i-1][j])
        val scanner = new Scanner(System.in)
        val n = scanner.nextInt()
        val dp = Array.ofDim[Int](n, n)
        val rewards = Array.ofDim[Int](n, n)

        (0 until n).foreach(row => {
            (0 until row + 1).foreach(col => {
                rewards(row)(col) = scanner.nextInt()
            })
        })
        dp(0)(0) = rewards(0)(0)
        (1 until n).foreach(row => {
            dp(row)(0) = rewards(row)(0) + dp(row - 1)(0)
            (1 until row).foreach(col => {
                dp(row)(col) = rewards(row)(col) + math.max(dp(row - 1)(col - 1), dp(row - 1)(col))
            })
            dp(row)(row) = rewards(row)(row) + dp(row - 1)(row - 1)
        })
        println(dp(n - 1).max)
    }
}
