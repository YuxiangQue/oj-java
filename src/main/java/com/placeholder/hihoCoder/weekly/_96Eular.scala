package com.placeholder.hihoCoder.weekly

import scala.util.control.Breaks._

class _96Eular {

    def phi(n: Int): Array[Int] = {
        val isPrime = Array.fill(n + 1)(true)
        val phi = new Array[Int](n + 1) // phi[n]表示n的欧拉函数
        val primeList = new scala.collection.mutable.ListBuffer[Int]

        (2 until n).foreach(i => {
            if (isPrime(i)) {
                primeList += i
                phi(i) = i - 1 // 质数的欧拉函数为p-1
            }
            breakable {
                for (prime <- primeList) {
                    if (i * prime > n) {
                        break
                    }
                    isPrime(i * prime) = false
                    if (i % prime == 0) {
                        // primeList[j]是i的约数，φ(n*p) = φ(n) * p
                        phi(i * prime) = phi(i) * prime
                        break
                    } else {
                        // primeList[j]不是i的约数，φ(n*p) = φ(n) * (p-1)
                        phi(i * prime) = phi(i) * (prime - 1)
                    }
                }
            }
        })
        phi
    }
}
