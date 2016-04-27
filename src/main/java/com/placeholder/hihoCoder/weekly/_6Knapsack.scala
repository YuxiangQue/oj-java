package com.placeholder.hihoCoder.weekly

import java.util.Scanner

/**
  * http://hihocoder.com/contest/hiho6/problem/1
  * 0-1背包
  *
  */
object _6Knapsack {
    def main(args: Array[String]) {
        // dp[i][x] = max(dp[i-1][x-need[i]] + value[i], dp[i-1][x])
        val scanner = new Scanner(System.in)
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        val need = Array.ofDim[Int](n + 1)
        val value = Array.ofDim[Int](n + 1)

        for (i <- 1 until n + 1) {
            need(i) = scanner.nextInt()
            value(i) = scanner.nextInt()
        }

        val dp = Array.ofDim[Int](n + 1, m + 1)
        for (i <- 1 until n + 1) {
            for (x <- 1 until m + 1) {
                if (x < need(i)) {
                    dp(i)(x) = dp(i - 1)(x)
                } else {
                    dp(i)(x) = Math.max(value(i) + dp(i - 1)(x - need(i)), dp(i - 1)(x))
                }
            }
        }
        println(dp(n).max)
    }
}
