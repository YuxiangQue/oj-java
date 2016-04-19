package com.placeholder.hihoCoder.weekly;


import com.placeholder.common.NumberTheory;

/**
 * http://hihocoder.com/discuss/question/2857/ Divisors
 *
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class _89Divisors {

    // 生成
    private int[] primes = new int[41];
    private int primeIndex = 0;
    // n = p1^n1 * p2^n2 * p3^n3 * ... * pk^nk
    // BOTH_WIN = (n1+1)*(n2+1)*(n3+1)* ... * (nk+1)
    // 枚举质因子数量，在使得n不超过N的情况下，使得D尽可能大
    private int maxDivisors = 0;
    private int result = 0;

    public static void main(String[] args) {
        _89Divisors divisors = new _89Divisors();
        divisors.generatePrimes();
        divisors.dfs(100);
    }

    private int nextPrime() {
        return primes[primeIndex++];
    }

    private void generatePrimes() {
        int i = 2;
        while (primeIndex < 41) {
            if (NumberTheory.isPrime(i)) {
                primes[primeIndex++] = i;
            }
            ++i;
        }
    }

    private void dfs(int n, int prime, int now, int D) {
        if (prime > n) {
            return;
        }
        if (D > maxDivisors || (D == maxDivisors && now < result)) {
            maxDivisors = D;
            result = now;
        }
        int i = 0;
        while (true) {
            int now1 = (int) (now * Math.pow(prime, i));
            if (now1 > n)
                break;
            dfs(n, nextPrime(), now1, D * (i + 1));
            ++i;
        }
    }

    private int dfs(int n) {
        dfs(n, nextPrime(), 0, 1);
        return result;
    }
}
