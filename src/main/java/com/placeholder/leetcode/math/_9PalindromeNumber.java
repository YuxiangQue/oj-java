package com.placeholder.leetcode.math;

/**
 * @author 阙宇翔
 * @version 2016/2/24
 */
public class _9PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(new _9PalindromeNumber().isPalindrome(123));
        System.out.println(new _9PalindromeNumber().isPalindrome(121));
    }

    public boolean isPalindrome(int x) {
        while (x != 0) {
            int high = x / 10;
            int low = x % 10;
            x = (x - low) / 10;
            if (high != low)
                return false;
        }
        return true;
    }
}
