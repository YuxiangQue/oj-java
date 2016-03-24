package com.placeholder.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _7ReverseInteger {
    public int reverse(int x) {
        long y = 0;
        while (x != 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        // 处理overflow
        if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE)
            return 0;
        return (int) y;
    }

    @Test
    public void test() {
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(-321, reverse(-123));
        Assert.assertEquals(-1, reverse(-100));
        Assert.assertEquals(0, reverse(0));
        Assert.assertEquals(1, reverse(100));
        Assert.assertEquals(0, reverse(1534236469)); // overflow
    }
}
