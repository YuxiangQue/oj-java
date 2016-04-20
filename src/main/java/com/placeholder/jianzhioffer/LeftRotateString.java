package com.placeholder.jianzhioffer;

/**
 * @author yuxiangque
 * @version 2016/4/20
 */
public class LeftRotateString {


    public static void reverse(char[] string, int left, int right) {
        for (int i = 0; i < (right - left + 1) / 2; ++i) {
            char temp = string[left + i];
            string[left + i] = string[right - i];
            string[right - i] = temp;
        }
    }

    public static String LeftRotateString(String str, int n) {
        if (str == null || str.length() == 0)
            return str;
        n = n % str.length();
        char[] string = str.toCharArray();
        reverse(string, 0, n - 1);
        reverse(string, n, string.length - 1);
        reverse(string, 0, string.length - 1);
        return String.valueOf(string);
    }

    public static void main(String[] args) {
        System.out.println(LeftRotateString("abcXYZdef", 3));
    }
}
