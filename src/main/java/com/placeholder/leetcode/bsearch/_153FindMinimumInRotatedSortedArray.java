package com.placeholder.leetcode.bsearch;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _153FindMinimumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        int n = nums.length;
        int lower = 0;
        int upper = n - 1;
        while (lower < upper) {
            int mid = lower + (upper - lower) / 2;

            // odd
            // 1 2 4 5 6  #0
            // 6 1 2 4 5  #1
            // 5 6 1 2 4  #1
            // 4 5 6 1 2  #2
            // 2 4 5 6 1  #2

            // even
            // 1 2 4 5 6 7  #0
            // 7 1 2 4 5 6  #1
            // 6 7 1 2 4 5  #1
            // 5 6 7 1 2 4  #2
            // 4 5 6 7 1 2  #2
            // 2 4 5 6 7 1  #2
            if (nums[mid] > nums[lower] && nums[mid] < nums[upper]) { // #0
                return nums[lower];
            } else if (nums[mid] <= nums[lower] && nums[mid] < nums[upper]) {  // #1
                upper = mid;
            } else if (nums[mid] >= nums[lower] && nums[mid] > nums[upper]) {  // #2
                lower = mid + 1;
            }
        }
        return nums[lower];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4, 5, 6, 7, 1, 2}));
        System.out.println(findMin(new int[]{6, 1, 2, 4, 5}));
        System.out.println(findMin(new int[]{5, 6, 1, 2, 4}));
        System.out.println(findMin(new int[]{4, 5, 6, 1, 2}));
        System.out.println(findMin(new int[]{2, 4, 5, 6, 1}));
    }
}
