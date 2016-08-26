package com.placeholder.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/4/21
 */
public class _342PowerOfFour {

    boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    // 0 4 16
    boolean isPowerOfFour(int n) {
        if (!isPowerOfTwo(n)) {
            return false;
        }
        int sqrt = (int) Math.ceil(Math.sqrt(n * 1.0));
        return sqrt * sqrt == n;
    }

    @Test
    public void test() {
        int[] powerOfFours = new int[]{1, 4, 16, 64, 256, 1024, 4096};
        for (int i = 0; i < 4000; i++) {
            boolean answer = false;
            for (int j : powerOfFours) {
                if (i == j) {
                    answer = true;
                    break;
                }
            }
            Assert.assertEquals("" + i + "", answer, isPowerOfFour(i));
        }
    }
}
