package com.placeholder.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/super-ugly-number/
 *
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _313SuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        int[] multiply = new int[primes.length];
        uglyNumbers[0] = 1;
        int nextUglyIndex = 1;
        while (nextUglyIndex < n) {
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

    @Test
    public void test() {
        Assert.assertEquals(32, nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }
}
