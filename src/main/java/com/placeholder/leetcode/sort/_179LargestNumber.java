package com.placeholder.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/largest-number/
 *
 * @author yuxiangque
 * @version 2016/4/14
 */
public class _179LargestNumber {

    // [3, 30, 34, 5, 9]
    // 9 5 34 30 3
    public String largestNumber(int[] nums) {
        String largest = IntStream.of(nums)
                .mapToObj(String::valueOf)
                .sorted((o1, o2) -> {
                    String num1 = o2 + o1;
                    String num2 = o1 + o2;
                    for (int i = 0; i < num1.length(); i++) {
                        if (num1.charAt(i) > num2.charAt(i))
                            return 1;
                        else if (num1.charAt(i) < num2.charAt(i))
                            return -1;
                    }
                    return 0;
                })
                .reduce("", (largestNumber, num) -> largestNumber + num);
        int noneZero = 0;
        while (noneZero < largest.length() && largest.charAt(noneZero) == '0') {
            ++noneZero;
        }
        largest = largest.substring(noneZero);
        if (largest.length() == 0)
            return "0";
        return largest;
    }

    @Test
    public void test() {
        Assert.assertEquals("9534330", largestNumber(new int[]{3, 30, 34, 5, 9}));
        Assert.assertEquals("999999999999999998999999997", largestNumber(new int[]{999999998, 999999997, 999999999}));
        Assert.assertEquals("0", largestNumber(new int[]{0, 0, 0}));
        Assert.assertEquals("100", largestNumber(new int[]{0, 0, 1}));
    }
}
