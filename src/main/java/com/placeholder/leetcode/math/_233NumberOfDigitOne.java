package com.placeholder.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://discuss.leetcode.com/topic/57378/java-solution-with-both-iterative-and-recursive-approach
 */
public class _233NumberOfDigitOne {

    // step 1 = 100 + 2(99) + (54)
    // step 2 = 100 + 2( 10 + 9(9) + (9)) + (10 + 5(9) + (4))
    // final step = 100 + 2( 10 + 9*1 + 1) + (10 + 5*1 + 1) since for number <= 9 will return 1
    // result = 100 + 2 (20) + 16 = 156 ans
    private int countDigitOne(int num) {
        if (num <= 0)
            return 0;
        int constant = 0;
        int val = num;
        int position = 1;
        while (val / 10 != 0) {
            val /= 10;
            position *= 10;
        }
        if (val == 1) {
            constant = num % position + 1;
        } else {
            constant = position;
        }
        return constant + val * countDigitOne(position - 1) + countDigitOne(num % position);
    }

    @Test
    public void test() {
        Assert.assertEquals(0, countDigitOne(-1));
        Assert.assertEquals(0, countDigitOne(0));
        Assert.assertEquals(1, countDigitOne(1));
        Assert.assertEquals(2, countDigitOne(10));
        Assert.assertEquals(16, countDigitOne(54));
        Assert.assertEquals(20, countDigitOne(99));
        Assert.assertEquals(156, countDigitOne(254));
    }
}
