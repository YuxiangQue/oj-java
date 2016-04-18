package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/wildcard-matching/
 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0&index=39&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
 *
 * @author yuxiangque
 * @version 2016/4/18
 */
public class _44WildcardMatching {

    public static boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        // Replace multiple * with one *
        int writeIndex = 0;
        boolean isFirst = true;
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                } else {
                    // Skip non first *
                }
            } else {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            }
        }
        boolean[][] T = new boolean[str.length + 1][writeIndex + 1];
        T[0][0] = true;

        if (writeIndex > 0 && pattern[0] == '*') {
            T[0][1] = true;
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j - 1] == '?' || str[i - 1] == pattern[j - 1]) {
                    T[i][j] = T[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    T[i][j] = T[i - 1][j] || T[i][j - 1];
                }
            }
        }
        return T[str.length][writeIndex];
    }

    @Test
    public void test() {
        Assert.assertEquals(false, isMatch("aa", "a"));
        Assert.assertEquals(true, isMatch("aa", "aa"));
        Assert.assertEquals(false, isMatch("aaa", "aa"));
        Assert.assertEquals(true, isMatch("aa", "*"));
        Assert.assertEquals(true, isMatch("aa", "a*"));
        Assert.assertEquals(true, isMatch("ab", "?*"));
        Assert.assertEquals(false, isMatch("aab", "c*a*b"));
    }
}
