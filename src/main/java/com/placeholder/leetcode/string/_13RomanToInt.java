package com.placeholder.leetcode.string;

/**
 * Created by yuxiangque on 2016/3/18.
 */
public class _13RomanToInt {
    int romanToInt(String s) {
        int value = 0;
        int index = 0;
        while (s.charAt(index) != '\0') {
            int num = 0;
            char next = s.charAt(index + 1);
            switch (s.charAt(index)) {
                case 'I':
                    num = ((next != 'V' && next != 'X') ? 1 : -1);
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = ((next != 'L' && next != 'C') ? 10 : -10);
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = ((next != 'D' && next != 'M') ? 100 : -100);
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            value += num;
            ++index;
        }
        return value;
    }
}
