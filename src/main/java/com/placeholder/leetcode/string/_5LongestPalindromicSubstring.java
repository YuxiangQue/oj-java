package com.placeholder.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * http://blog.csdn.net/feliciafay/article/details/16984031
 *
 * @author yuxiangque
 * @version 2016/4/5
 */
public class _5LongestPalindromicSubstring {

    // Define: S[i][j]   true iff the substring Si … Sj is a palindrome, otherwise false.
    // P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj )
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() == 0)
            return "";
        int n = s.length();
        int left = 0;
        int right = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            left = i;
            right = i;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                left = i;
                right = i + 1;
            }
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; ++i) {
                int j = i + len - 1;
                if (dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public boolean dp(String s, int i, int j) {
        if (i == j) {
            return true;
        }
        if (i + 1 == j) {
            return s.charAt(i) == s.charAt(j);
        }
        return dp(s, i + 1, j - 1) && s.charAt(i) == s.charAt(j);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        int max = -1;
        int left = 0;
        int right = 0;
        // 奇数
        for (int mid = 0; mid < s.length(); ++mid) {
            int len = 1;
            while (mid - len >= 0 && mid + len < s.length() && s.charAt(mid - len) == s.charAt(mid + len)) {
                ++len;
            }
            --len;
            if (len > max) {
                max = len;
                left = mid - len;
                right = mid + len;
            }
        }
        // 偶数
        for (int mid = 0; mid < s.length(); ++mid) {
            int len = 1;
            while (mid - len + 1 >= 0 && mid + len < s.length() && s.charAt(mid - len + 1) == s.charAt(mid + len)) {
                ++len;
            }
            --len;
            if (len > max) {
                max = len;
                left = mid - len + 1;
                right = mid + len;
            }
        }
        return s.substring(left, right + 1);
    }

    @Test
    public void test() {
        Assert.assertEquals("", longestPalindromeDP(""));
        Assert.assertEquals("a", longestPalindromeDP("a"));
        Assert.assertEquals("bcb", longestPalindromeDP("abcb"));
        Assert.assertEquals("aba", longestPalindromeDP("aba"));
        Assert.assertEquals("aba", longestPalindromeDP("abade"));
        Assert.assertEquals("deded", longestPalindromeDP("abadeded"));
        Assert.assertEquals("abba", longestPalindromeDP("abba"));
    }
}
