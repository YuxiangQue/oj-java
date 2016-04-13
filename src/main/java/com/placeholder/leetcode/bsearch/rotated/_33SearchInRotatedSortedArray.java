package com.placeholder.leetcode.bsearch.rotated;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/3/25
 */
public class _33SearchInRotatedSortedArray {

    public static int findMinIndex(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[left] <= nums[right]) {
                break;
            }
            if (nums[middle] > nums[left]) {
                left = middle + 1;
            } else if (nums[middle] < nums[left]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
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
        Assert.assertEquals(-1, search(new int[]{1}, 0));
        Assert.assertEquals(-1, search(new int[]{1, 1}, 0));
        Assert.assertEquals(4, search(new int[]{4, 5, 6, 7, 1, 2}, 1));
        Assert.assertEquals(5, search(new int[]{4, 5, 6, 7, 1, 2}, 2));
        Assert.assertEquals(-1, search(new int[]{4, 5, 6, 7, 1, 2}, 3));
        Assert.assertEquals(0, search(new int[]{4, 5, 6, 7, 1, 2}, 4));
        Assert.assertEquals(1, search(new int[]{4, 5, 6, 7, 1, 2}, 5));
        Assert.assertEquals(2, search(new int[]{4, 5, 6, 7, 1, 2}, 6));
        Assert.assertEquals(3, search(new int[]{4, 5, 6, 7, 1, 2}, 7));
    }
}
