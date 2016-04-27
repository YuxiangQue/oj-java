package com.placeholder.hihoCoder.weekly

import java.util.Scanner

/**
  * http://hihocoder.com/contest/hiho7/problem/1
  *
  */
object _7CompletePack {

    def step1(): Unit = {
        // dp[i][x] = max(dp[i-1][x-k*need[i]] + value[i], dp[i-1][x])
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
                // k =0
                var max = dp(i - 1)(x)
                var k = 1
                while (k * need(i) < x) {
                    max = Math.max(max, Math.max(k * value(i) + dp(i - 1)(x - k * need(i)), dp(i - 1)(x)))
                    k += 1
                }
                dp(i)(x) = max
            }
        }
        println(dp(n).max)
    }

    def main(args: Array[String]) {
        step2()
    }

    def step2(): Unit = {
        // dp[i][x]         = max(dp[i-1][x-k*need[i]]+k*value[i]), 0<=k<=x/need(i)
        // dp[i][x-need[i]] = max(dp[i-1][x-k*need[i]]+k*value[i]), 1<=k<=x/need(i)

        // 要不要再选一件第i种奖品
        // dp[i][x]         = max(dp[i][x-need[i]]+value[i], dp[i-1][x])
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
                    dp(i)(x) = Math.max(value(i) + dp(i)(x - need(i)), dp(i - 1)(x))
                }
            }
        }
        println(dp(n).max)
    }
}
