package com.placeholder.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _233NumberOfDigitOne {
    public int countDigitOne(int n) {
        String str = String.valueOf(n);
        return countDigitOne(str, 0);
    }

    //  21345
    //  10000 - 19999
    //
    private int countDigitOne(String str, int highestIndex) {

        if (highestIndex == str.length())
            return 0;

        int highest = str.charAt(highestIndex) - '0'; // 最高位
        int length = str.length() - highestIndex;

        int countNonrecursive = highest * (length - 1) * (int) Math.pow(10, length - 2);
        int countRecursive = countDigitOne(str, highestIndex + 1);
        return countNonrecursive + countRecursive;
    }

    @Test
    public void test() {
        Assert.assertEquals(6, countDigitOne(13));
    }
}
