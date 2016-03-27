package com.placeholder.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _201BitwiseANDOfNumbersRange {

    @Test
    public void test() {
        Assert.assertEquals(4, rangeBitwiseAnd(5, 7));
        Assert.assertEquals(1, rangeBitwiseAnd(1, 1));
        Assert.assertEquals(2, rangeBitwiseAnd(2, 2));
        Assert.assertEquals(0, rangeBitwiseAnd(1, 2));
        Assert.assertEquals(0, rangeBitwiseAnd(0, 65536));
    }

    // TimeLimit
    public int rangeBitwiseAnd1(int m, int n) {
        int result = ~0;
        while (m <= n) {
            result = result & m;
            ++m;
        }
        return result;
    }

    // https://leetcode.com/discuss/32115/bit-operation-solution-java
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) {
            return 0;
        }
        int moveOffset = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            moveOffset++;
        }
        return m << moveOffset;
    }
}
