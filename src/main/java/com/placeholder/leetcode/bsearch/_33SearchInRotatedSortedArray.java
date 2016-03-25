package com.placeholder.leetcode.bsearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author yuxiangque
 * @version 2016/3/25
 */
public class _33SearchInRotatedSortedArray {

    public static int findMinIndex(int[] nums) {
        int n = nums.length;
        int lower = 0;
        int upper = n - 1;
        int mid = lower;
        while (nums[lower] >= nums[upper]) {
            if (upper == lower + 1) {
                mid = upper;
                break;
            }
            mid = lower + (upper - lower) / 2;
            if (nums[mid] >= nums[lower]) {
                lower = mid;
            } else if (nums[mid] < nums[lower]) {
                upper = mid;
            }
        }
        return mid;
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int minIndex = findMinIndex(nums);
        int left = Arrays.binarySearch(nums, 0, minIndex, target);
        if (left >= 0)
            return left;
        int right = Arrays.binarySearch(nums, minIndex, nums.length, target);
        if (right >= 0)
            return right;
        return -1;
    }

    @Test
    public void test() {
        // 无重复
        Assert.assertEquals(-1, search(new int[]{1}, 0));
        Assert.assertEquals(-1, search(new int[]{1, 1}, 0));
        Assert.assertEquals(5, search(new int[]{4, 5, 6, 7, 1, 2}, 1));
        Assert.assertEquals(4, search(new int[]{4, 5, 6, 7, 1, 2}, 2));
        Assert.assertEquals(-1, search(new int[]{4, 5, 6, 7, 1, 2}, 3));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 4));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 5));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 6));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 7));
    }
}
