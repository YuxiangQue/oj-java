package com.placeholder.leetcode.dp;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _152MaximumProductSubarray {

    private static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max(max * nums[i], min * nums[i], nums[i]);
            min = min(max * nums[i], min * nums[i], nums[i]);
            max = temp;
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
}
