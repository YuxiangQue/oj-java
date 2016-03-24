package com.placeholder.leetcode.dp;

/**
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class _53MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            if (sum > maxSum)
                maxSum = sum;
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
}
