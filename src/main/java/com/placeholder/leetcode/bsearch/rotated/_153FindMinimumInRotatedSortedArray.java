package com.placeholder.leetcode.bsearch.rotated;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * <p>
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _153FindMinimumInRotatedSortedArray {


    public static int findMin2(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            int middle = left + (right - left) / 2;
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

    // You may assume no duplicate exists in the array.
    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left] && nums[mid] < nums[right]) { // #升序
                return nums[left];
            } else if (nums[mid] <= nums[left] && nums[mid] < nums[right]) {  // #中间小两边大
                right = mid;
            } else if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {  // #中间大两边小
                left = mid + 1;
            }
        }
        return nums[left];
    }

    @Test
    public void test() {
        // Assert.assertEquals(1, findMin2(new int[]{4, 5, 6, 7, 1, 2}));
        Assert.assertEquals(1, findMin2(new int[]{6, 1, 2, 4, 5}));
        Assert.assertEquals(1, findMin2(new int[]{5, 6, 1, 2, 4}));
        Assert.assertEquals(1, findMin2(new int[]{4, 5, 6, 1, 2}));
        Assert.assertEquals(1, findMin2(new int[]{2, 4, 5, 6, 1}));
    }
}
