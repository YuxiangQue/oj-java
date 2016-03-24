package com.placeholder.leetcode.math;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _258AddDigits {
    public static void main(String[] args) {
        System.out.println(new _258AddDigits().addDigits(38));
        System.out.println(new _258AddDigits().addDigits(1));
        System.out.println(new _258AddDigits().addDigits(19));
    }

    /**
     * https://leetcode.com/problems/add-digits/
     */
    public int addDigits(int num) {
        while (true) {
            String numStr = ("" + num);
            num = 0;
            int len = numStr.length();
            for (int i = 0; i < len; ++i) {
                num += numStr.charAt(i) - '0';
            }
            if (num / 10 == 0)
                return num;
        }
    }
}
