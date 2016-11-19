package com.placeholder.leetcode.dp;

/**
 * http://blog.csdn.net/worldwindjp/article/details/19770281
 */
public class _112DistinctSubsequences {
    public static int numDistinct(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        int[][] dp = new int[s1.length + 1][t1.length + 1];
        dp[0][0] = 1;
        for (int i = 1; i < s1.length + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < s1.length + 1; i++) {
            for (int j = 1; j < t1.length + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s1[i - 1] == t1[j - 1])
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }
        return dp[s1.length][t1.length];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("", ""));
        System.out.println(numDistinct("ab", ""));
        System.out.println(numDistinct("ab", "a"));
        System.out.println(numDistinct("aaa", "a"));
        System.out.println(numDistinct("rabbbit", "rabit"));
    }

}
