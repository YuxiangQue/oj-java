package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * #dfs
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/4/13
 * @see _300LongestIncreasingSubsequence
 */
public class _334IncreasingTripletSubsequence {


    @Test
    public void test() {
        Assert.assertEquals(true, increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(false, increasingTriplet(new int[]{5, 4, 3, 2, 1}));
    }


    // dfs[i] = max{dfs[j]+1} s.t.  0<=j<i, a[j] < a[i]
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
            if (max >= 3) {
                return true;
            }
        }
        return false;
    }
}
