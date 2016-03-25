package com.placeholder.leetcode.bsearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * TODO
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _81SearchInRotatedSortedArray {


    // 顺序搜索
    private static int findMinSeq(int[] nums, int lower, int upper) {
        int min = nums[lower];
        for (int i = lower + 1; i <= upper; i++) {
            if (nums[i] < min)
                min = nums[i];
        }
        return min;
    }

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
            if (nums[mid] == nums[lower] && nums[mid] == nums[upper]) {
                return findMinSeq(nums, lower, upper);
            }
            if (nums[mid] >= nums[lower]) {
                lower = mid;
            } else if (nums[mid] < nums[lower]) {
                upper = mid;
            }
        }
        return mid;
    }

    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1)
            return nums[0] == target;
        int minIndex = findMinIndex(nums);
        return Arrays.binarySearch(nums, minIndex, nums.length, target) >= 0 ||
                Arrays.binarySearch(nums, 0, minIndex, target) >= 0;
    }

    @Test
    public void test() {
        // 无重复
        Assert.assertEquals(false, search(new int[]{1}, 0));
        Assert.assertEquals(false, search(new int[]{1, 1}, 0));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 1));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 2));
        Assert.assertEquals(false, search(new int[]{4, 5, 6, 7, 1, 2}, 3));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 4));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 5));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 6));
        Assert.assertEquals(true, search(new int[]{4, 5, 6, 7, 1, 2}, 7));

        // 有重复
        Assert.assertEquals(true, search(new int[]{4, 4, 6, 7, 1, 2}, 7));
        Assert.assertEquals(true, search(new int[]{4, 4, 4, 7, 1, 2}, 4));
        Assert.assertEquals(true, search(new int[]{1, 3, 1, 1, 1}, 3));
        Assert.assertEquals(true, search(new int[]{4, 4, 4, 7, 1, 3}, 3));
    }
}
