package com.placeholder.leetcode.dp;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * https://www.youtube.com/watch?v=l3hda49XcDE
 * TODO
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _10ReularExptressionMatching {
    public static boolean isMatch(String s, String p) {
        return dp(s.toCharArray(), p.toCharArray());
    }


    private static boolean dp(char[] text, char[] pattern) {
        int textLength = text.length;
        int patternLength = pattern.length;
        boolean[][] T = new boolean[textLength + 1][patternLength + 1];

        T[0][0] = true;

        for (int j = 1; j <= patternLength; j++) {
            if (pattern[j - 1] == '*') {
                T[0][j] = T[0][j - 2];
            }
        }

        for (int i = 1; i <= textLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                if (text[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    T[i][j] = T[i - 1][j - 1];
                }
                if (pattern[j - 1] == '*') {
                    T[i][j] = T[i][j - 2]; // 0 times
                    if ((text[i - 1] == pattern[j - 2] || pattern[j - 2] == '.')) { // more than 1 times
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                }

            }
        }
        return T[textLength][patternLength];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b") == true);
        System.out.println(isMatch("aab", "c*a*b") == true);
    }
}
