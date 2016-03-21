package com.placeholder.leetcode;

/**
 * Created by yuxiangque on 2016/3/14.
 */
public class _9PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int temp = x;
        int y = 0;
        while (temp != 0) {
            y = y * 10 + temp % 10;
            temp /= 10;
        }
        return x == y;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(11));
        System.out.println(isPalindrome(12));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(122));
        System.out.println(isPalindrome(15452));
        System.out.println(isPalindrome(-2147447412));
    }
}
