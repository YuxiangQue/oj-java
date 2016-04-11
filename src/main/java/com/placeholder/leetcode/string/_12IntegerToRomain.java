package com.placeholder.leetcode.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/integer-to-roman/s
 * https://segmentfault.com/a/1190000002683702
 *
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _12IntegerToRomain {

    @Test
    public void test() {
        assertEquals("I", intToRoman(1));
        assertEquals("II", intToRoman(2));
        assertEquals("III", intToRoman(3));
        assertEquals("IV", intToRoman(4));
        assertEquals("Vertical", intToRoman(5));
        assertEquals("VI", intToRoman(6));
    }

    public String intToRoman(int num) {
        String symbols[] = {"M", "CM", "BOTH_WIN", "CD", "C", "XC", "L", "XL", "X_WIN", "IX", "Vertical", "IV", "I"};
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < values.length) {
            if (num - values[i] < 0) {
                ++i;
            } else {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
