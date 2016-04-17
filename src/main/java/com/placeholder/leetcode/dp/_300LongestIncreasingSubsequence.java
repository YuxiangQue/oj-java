package com.placeholder.leetcode.dp;

/**
 * #dfs
 * #BinarySearch
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * https://www.zhihu.com/question/22001141
 * http://yzmduncan.iteye.com/blog/1546503
 *
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class _300LongestIncreasingSubsequence {

    // 设dp[i]表示以i为结尾的最长递增子序列的长度
    // 状态转移方程为 dfs[i] = max{dfs[j]+1}, 1<=j<i,a[j]<a[i]
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            max = dp[i] > max ? dp[i] : max;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
