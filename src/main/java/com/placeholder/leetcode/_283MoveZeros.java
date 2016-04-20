package com.placeholder.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/move-zeroes/
 * #Two Pointer
 * #Array
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _283MoveZeros {

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        Assert.assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
    }

    void moveZeroes(int[] nums) {
        int length = nums.length;
        int left = 0;  // 下一个放置非0的下标
        int right = 0;
        while (right < length) {
            if (nums[right] != 0) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                ++left;
                ++right;
            } else {
                ++right;
            }
        }
    }
}
