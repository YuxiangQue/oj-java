package com.placeholder.leetcode.bsearch;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
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

    public static int findMin(int[] nums) {
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

            // 1 1 3
            // 3 3 1
            // 1 1 3 3
            // 3 3 1 1
            if (nums[mid] >= nums[lower]) {  // 这里的=号是需要的
                lower = mid;
            } else if (nums[mid] < nums[lower]) {
                upper = mid;
            }
        }
        return nums[mid];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4, 5, 6, 7, 1, 2}));
        System.out.println(findMin(new int[]{6, 1, 2, 4, 5}));
        System.out.println(findMin(new int[]{5, 6, 1, 2, 4}));
        System.out.println(findMin(new int[]{4, 5, 6, 1, 2}));
        System.out.println(findMin(new int[]{2, 4, 5, 6, 1}));
        System.out.println(findMin(new int[]{3, 1, 1}));
        System.out.println(findMin(new int[]{3, 1, 1, 1}));
        System.out.println(findMin(new int[]{1, 3, 3}));
        System.out.println(findMin(new int[]{1, 1, 1, 3}));
        System.out.println(findMin(new int[]{3, 3, 1}));
    }
}
