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

    // dp
    public String longestPalindromeDP(String s) {
        dp(s, 0, 0);
        return "";
    }

    public boolean dp(String s, int i, int j) {
        if (i == j) {
            return true;
        }
        if (i + 1 == j) {
            return s.charAt(i) == s.charAt(i + 1);
        }
        return s.charAt(i + 1) == s.charAt(j - 1);
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
                left = mid - len;
                right = mid + len;
            }
        }
        return s.substring(left, right + 1);
    }

    @Test
    public void test() {
        Assert.assertEquals("", longestPalindrome(""));
        Assert.assertEquals("a", longestPalindrome("a"));
        Assert.assertEquals("a", longestPalindrome("ab"));
        Assert.assertEquals("aba", longestPalindrome("aba"));
        Assert.assertEquals("aba", longestPalindrome("abade"));
        Assert.assertEquals("deded", longestPalindrome("abadeded"));
        Assert.assertEquals("abba", longestPalindrome("abba"));
    }
}
