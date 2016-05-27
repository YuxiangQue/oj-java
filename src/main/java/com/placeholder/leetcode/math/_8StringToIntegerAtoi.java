package com.placeholder.leetcode.math;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _8StringToIntegerAtoi {

    private static final int STATE_START = 0;   // 只能接受空格、符号、数字；
    private static final int STATE_SIGN = 1;    // 只能接受符号
    private static final int STATE_DIGIT = 2;   // 只能接受空格、数字、结束
    private static final int STATE_STOP = 3;    // 只能接受空格、结束；
    private static final int STATE_DIGIT_0 = 4; // 只接受0
    private static final int INT_MIN = -(Integer.MAX_VALUE + 1);
    private static final int INT_MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new _8StringToIntegerAtoi().myAtoi("123"));
        System.out.println(new _8StringToIntegerAtoi().myAtoi("-123"));
        System.out.println(new _8StringToIntegerAtoi().myAtoi(" 123  "));
        System.out.println(new _8StringToIntegerAtoi().myAtoi("010"));
        System.out.println(new _8StringToIntegerAtoi().myAtoi("-2147483649"));
        System.out.println(new _8StringToIntegerAtoi().myAtoi("9223372036854775809"));
    }

    public int myAtoi(String str) {
        int state = STATE_START;
        int length = str.length();
        int sign = 1;
        long value = 0;
        for (int p = 0; p < length; ) {
            char ch = str.charAt(p);
            switch (state) {
                case STATE_START:
                    if (ch == ' ') {
                        ++p; // consume
                    } else if (ch == '+' || ch == '-') {
                        state = STATE_SIGN;
                    } else if (ch == '0') {
                        state = STATE_DIGIT_0;
                    } else if (ch > '0' && ch <= '9') {
                        state = STATE_DIGIT;
                    } else {
                        return 0;
                    }
                    break;
                case STATE_SIGN:
                    if (ch == '+') {
                        ++p; // consume
                        sign = 1;
                        state = STATE_DIGIT_0;
                    } else if (ch == '-') {
                        ++p;
                        sign = -1;
                        state = STATE_DIGIT_0;
                    } else {
                        return 0;
                    }
                    break;
                case STATE_DIGIT_0:
                    if (ch == '0') {
                        ++p; // consume
                    } else {
                        state = STATE_DIGIT;
                    }
                    break;
                case STATE_DIGIT:
                    if (ch >= '0' && ch <= '9') {
                        ++p;
                        value = value * 10 + (ch - '0');
                        if (value * sign > INT_MAX) {
                            return INT_MAX;
                        } else if (value * sign < INT_MIN) {
                            return INT_MIN;
                        }
                    } else {
                        state = STATE_STOP;
                    }
                    break;
                case STATE_STOP:
                    ++p; // consume
                    break;
            }
        }
        value *= sign;
        return (int) value;
    }
}
