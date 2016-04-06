package com.placeholder.leetcode.dp;


/**
 * http://blog.csdn.net/xudli/article/details/45886721
 * https://leetcode.com/problems/house-robber-ii/
 *
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _213HouseRobber2 {
    public static int helper(int[] nums, int begin, int end) {

        int n = end - begin + 1;
        int[] dp = new int[n];
        dp[0] = nums[begin];
        dp[1] = Math.max(nums[begin], nums[begin + 1]);
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.max(nums[i + begin] + dp[i - 2], dp[i - 1]);
        }
        int max = dp[0];
        for (int i = 1; i < dp.length; ++i) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        return Math.max(helper(nums, 1, nums.length - 1), helper(nums, 0, nums.length - 2));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(rob(nums));
        nums = new int[]{1, 2, 3, 3};
        System.out.println(rob(nums));
        nums = new int[]{};
        System.out.println(rob(nums));
        nums = new int[]{1, 1, 1};
        System.out.println(rob(nums));
    }
}
