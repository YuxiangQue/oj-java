package com.placeholder.leetcode.backtracking;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * TODO
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _10ReularExptressionMatching {
    public static boolean isMatch(String s, String p) {
        return dfs(s, 0, p, 0);
    }

    private static boolean dfs(String str, int strIndex, String pattern, int patternIndex) {
        if (strIndex == str.length() - 1 && patternIndex == pattern.length() - 1)
            return true;
        if (strIndex != str.length() - 1 && patternIndex == pattern.length() - 1)
            return false;
        if (pattern.charAt(patternIndex + 1) != '*') {
            boolean matchChar = pattern.charAt(patternIndex) == str.charAt(strIndex);
            boolean matchDot = pattern.charAt(patternIndex) == '.' && strIndex < str.length();
            if (matchChar || matchDot)
                return dfs(str, strIndex + 1, pattern, patternIndex + 1);
        } else {
            boolean matchChar = pattern.charAt(patternIndex) == str.charAt(strIndex);
            boolean matchDot = pattern.charAt(patternIndex) == '.' && strIndex < str.length();
            if (matchChar || matchDot) {
                return dfs(str, strIndex + 1, pattern, patternIndex + 2) ||  // move on the next state
                        dfs(str, strIndex + 1, pattern, patternIndex) ||  // stay on the current state
                        dfs(str, strIndex, pattern, patternIndex + 3);  // ignore a '*'
            } else {
                return dfs(str, strIndex + 1, pattern, patternIndex + 2); // ignore a '*'
            }
        }
        return false;
    }
}
