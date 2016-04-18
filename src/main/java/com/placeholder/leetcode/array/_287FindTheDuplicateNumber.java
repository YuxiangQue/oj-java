package com.placeholder.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _287FindTheDuplicateNumber {

    public static int countLessOrEqualThan(int[] nums, int k) {
        int counter = 0;
        for (int num : nums) {
            if (num <= k)
                ++counter;
        }
        return counter;
    }

    // bsearch
    public static int findDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int count = countLessOrEqualThan(nums, middle);
            if (count <= middle) {
                left = middle + 1;
            } else { // count > middle
                right = middle;
            }
        }
        return left;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, findDuplicate(new int[]{1, 2, 3, 3}));
    }
}
