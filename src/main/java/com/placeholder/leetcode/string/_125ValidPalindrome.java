package com.placeholder.leetcode.string;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _125ValidPalindrome {

    private static boolean isAlphaOrNumber(char ch) {
        if (ch >= 'a' && ch <= 'z')
            return true;
        if (ch >= 'A' && ch <= 'Z')
            return true;
        if (ch >= '0' && ch <= '9')
            return true;
        return false;
    }

    boolean isPalindrome(String s) {
        String lowerCase = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !isAlphaOrNumber(s.charAt(left))) {
                ++left;
            }
            while (left < right && !isAlphaOrNumber(s.charAt(right))) {
                --right;
            }
            if (lowerCase.charAt(left) != lowerCase.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
}
