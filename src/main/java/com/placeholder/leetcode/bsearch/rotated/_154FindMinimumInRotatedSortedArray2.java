package com.placeholder.leetcode.bsearch.rotated;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * #Array
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _154FindMinimumInRotatedSortedArray2 {

    // 顺序搜索
    private static int findMinSeq(int[] nums, int lower, int upper) {
        int min = nums[lower];
        for (int i = lower + 1; i <= upper; i++) {
            if (nums[i] < min)
                min = nums[i];
        }
        return min;
    }

    public static int findMin2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (nums[left] <= nums[right]) {
                return findMinSeq(nums, left, right);
            }
            int middle = left + (right - left) / 2;
            // nums[left] > nums[right]
            if (nums[middle] > nums[left]) {
                left = middle + 1;
            } else if (nums[middle] < nums[left]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return nums[left];
    }

    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int mid = left;
        while (nums[left] >= nums[right]) {
            if (right == left + 1) {
                mid = right;
                break;
            }
            mid = left + (right - left) / 2;
            if (nums[mid] == nums[left] && nums[mid] == nums[right]) {
                return findMinSeq(nums, left, right);
            }

            if (nums[mid] > nums[left]) {
                left = mid;
            } else if (nums[mid] < nums[left]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[mid];
    }

    @Test
    public void test() {
        Assert.assertEquals(1, findMin2(new int[]{2, 2, 1, 2, 2, 2}));
        Assert.assertEquals(1, findMin2(new int[]{4, 5, 6, 7, 1, 2}));
        Assert.assertEquals(1, findMin2(new int[]{6, 1, 2, 4, 5}));
        Assert.assertEquals(1, findMin2(new int[]{5, 6, 1, 2, 4}));
        Assert.assertEquals(1, findMin2(new int[]{4, 5, 6, 1, 2}));
        Assert.assertEquals(1, findMin2(new int[]{2, 4, 5, 6, 1}));
        Assert.assertEquals(1, findMin2(new int[]{3, 1, 1}));
        Assert.assertEquals(1, findMin2(new int[]{3, 1, 1, 1}));
        Assert.assertEquals(1, findMin2(new int[]{1, 3, 3}));
        Assert.assertEquals(1, findMin2(new int[]{1, 1, 1, 3}));
        Assert.assertEquals(1, findMin2(new int[]{3, 3, 1}));
    }
}
