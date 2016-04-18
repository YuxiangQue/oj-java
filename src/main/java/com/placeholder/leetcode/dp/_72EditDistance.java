package com.placeholder.leetcode.dp;

import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/4/17
 */
public class _72EditDistance {

    // https://leetcode.com/discuss/43398/20ms-detailed-explained-c-solutions-o-n-space
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void test() {
        System.out.println(minDistance("ab", "a"));
        System.out.println(minDistance("abc", "efg"));
        System.out.println(minDistance("plasma", "altruism"));
    }
}
