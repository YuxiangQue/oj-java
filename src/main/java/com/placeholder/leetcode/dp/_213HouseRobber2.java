package com.placeholder.leetcode.dp;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * Created by yuxiangque on 2016/3/18.
 */
public class _213HouseRobber2 {
    public static int rob(int[] nums) {
        int length = nums.length;
        if (length == 0)
            return 0;
        if (length == 1)
            return nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; ++i) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        int max = dp[0];
        for (int i = 1; i < length; ++i) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        System.out.println(rob(nums));
    }
}
