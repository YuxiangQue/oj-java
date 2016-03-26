package com.placeholder.leetcode.math;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 *
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _264UglyNumber2 {

    public int nthUglyNumber(int n) {
        int[] primes = new int[]{2, 3, 5};
        int[] uglyNumbers = new int[n];

        // 3. The key is how to maintain the order of the ugly numbers.
        // Try a similar approach of merging from three sorted lists: L1, L2, and L3.
        int[] multiply = new int[primes.length];
        uglyNumbers[0] = 1;
        int nextUglyIndex = 1;
        while (nextUglyIndex < n) {
            // Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
            int minNextUglyNumber = uglyNumbers[multiply[0]] * primes[0];
            for (int i = 1; i < primes.length; i++) {
                int nextUglyNumber = uglyNumbers[multiply[i]] * primes[i];
                minNextUglyNumber = nextUglyNumber < minNextUglyNumber ? nextUglyNumber : minNextUglyNumber;
            }
            uglyNumbers[nextUglyIndex] = minNextUglyNumber;
            for (int i = 0; i < primes.length; i++) {
                while (uglyNumbers[multiply[i]] * primes[i] <= minNextUglyNumber)
                    ++multiply[i];
            }
            ++nextUglyIndex;
        }
        return uglyNumbers[nextUglyIndex - 1];
    }
}
