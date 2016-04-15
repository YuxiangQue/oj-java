package com.placeholder.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuxiangque
 * @version 2016/4/15
 * @see com.placeholder.leetcode.math._264UglyNumber2
 */
public class _279PerfectSquares {

    public static int numSquares(int n) {
        if (n <= 0)
            return 0;
        int[] squares = squares(n);
        Set<Integer> candidates = new HashSet<>();
        candidates.add(0);
        int level = 1;
        while (true) {
            Set<Integer> result = new HashSet<>();
            for (int square : squares) {
                for (Integer candidate : candidates) {
                    int temp = candidate + square;
                    if (temp < n) {
                        result.add(temp);
                    } else if (temp == n) {
                        return level;
                    }
                }
            }
            candidates = result;
            if (candidates.size() == 0) {
                return 0;
            }
            ++level;
        }
    }

    public static int[] squares(int n) {
        int m = (int) Math.sqrt(n);
        int[] squares = new int[m];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = (i + 1) * (i + 1);
        }
        return squares;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, numSquares(0));
        Assert.assertEquals(1, numSquares(1));
        Assert.assertEquals(2, numSquares(2));
        Assert.assertEquals(3, numSquares(3));
        Assert.assertEquals(1, numSquares(4));
        Assert.assertEquals(2, numSquares(5));
        Assert.assertEquals(3, numSquares(12));  // 4 + 4 + 4
        Assert.assertEquals(2, numSquares(13));  // 4 + 9
    }
}
