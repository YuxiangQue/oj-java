package com.placeholder.leetcode.dp;

import org.junit.Test;

/**
 * http://www.tuicool.com/articles/uuyaIz
 * http://blog.csdn.net/doc_sgl/article/details/11714793
 */
public class _97InterleavingString {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length())
            return false;
        if (s1.length() == 0)
            return s2.equals(s3);
        if (s2.length() == 0)
            return s1.equals(s3);
        // return dfs(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0);
        return dp(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }

    private static boolean dp(char[] s1, char[] s2, char[] s3) {
        int m = s1.length, n = s2.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i < m + 1; i++) {
            if (s3[i - 1] == s1[i - 1])
                dp[i][0] = true;
            else
                break;
        }
        for (int i = 1; i < n + 1; i++) {
            if (s3[i - 1] == s2[i - 1])
                dp[0][i] = true;
            else
                break;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if ((dp[i][j - 1] && s3[i + j - 1] == s2[j - 1]) || (dp[i - 1][j] && s3[i + j - 1] == s1[i - 1]))
                    dp[i][j] = true;
            }
        }
        return dp[m][n];
    }

    private static boolean dfs(char[] s1, char[] s2, char[] s3, int i, int j) {
        if (i >= s1.length && j >= s2.length)
            return true;
        if (i >= s1.length) {
            char c3 = s3[i + j];
            char c2 = s2[j];
            return c3 == c2 && dfs(s1, s2, s3, i, j + 1);
        } else if (j >= s2.length) {
            char c1 = s1[i];
            char c3 = s3[i + j];
            return c3 == c1 && dfs(s1, s2, s3, i + 1, j);
        } else {
            char c3 = s3[i + j];
            char c1 = s1[i];
            char c2 = s2[j];
            if (c3 != c1 && c3 != c2) {
                return false;
            }
            if (c3 == c1 && c3 == c2) {
                return dfs(s1, s2, s3, i + 1, j) || dfs(s1, s2, s3, i, j + 1);
            } else if (c3 == c1) {
                return dfs(s1, s2, s3, i + 1, j);
            } else {
                return dfs(s1, s2, s3, i, j + 1);
            }
        }
    }

    @Test
    public void test() {
        System.out.println(isInterleave("", "", ""));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}
