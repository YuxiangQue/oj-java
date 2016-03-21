package com.placeholder.leetcode.math;

/**
 * https://leetcode.com/problems/valid-number/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _65ValidNumber {


    public static void main(String[] args) {
        System.out.println(new _65ValidNumber().isNumber("0"));
        System.out.println(new _65ValidNumber().isNumber(" 0.1 "));
        System.out.println(!new _65ValidNumber().isNumber("abc"));
        System.out.println(!new _65ValidNumber().isNumber("1 a"));
        System.out.println(new _65ValidNumber().isNumber("2e10"));
        System.out.println(!new _65ValidNumber().isNumber("."));
        System.out.println(!new _65ValidNumber().isNumber(" "));
        System.out.println(!new _65ValidNumber().isNumber("0e"));
        System.out.println(new _65ValidNumber().isNumber("3."));
        System.out.println(!new _65ValidNumber().isNumber("0 e"));
        System.out.println(!new _65ValidNumber().isNumber(". "));
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isBlank(char ch) {
        return ch == ' ';
    }

    private boolean isSign(char ch) {
        return ch == '+' || ch == '-';
    }

    private boolean isE(char ch) {
        return ch == 'e' || ch == 'E';
    }

    private boolean isDot(char ch) {
        return ch == '.';
    }

    // [-+]?[0-9]*(.[0-9]+)?(e[-+]?[0-9]+)?
    // https://leetcode.com/discuss/70510/a-simple-solution-in-python-based-on-dfa
    // http://normanyahq.github.io/static/files/valid_number_dfa.svg
    public boolean isNumber(String s) {
        int length = s.length();
        int state = 1;
        for (int p = 0; p < length; ) {
            char ch = s.charAt(p);
            switch (state) {
                case 1:
                    if (isBlank(ch)) {
                        ++p;
                    } else if (isDigit(ch)) {
                        ++p;
                        state = 3;
                    } else if (isSign(ch)) {
                        ++p;
                        state = 2;
                    } else if (isDot(ch)) {
                        ++p;
                        state = 4;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    if (isDigit(ch)) {
                        ++p;
                        state = 3;
                    } else if (isDot(ch)) {
                        ++p;
                        state = 4;
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    if (isDigit(ch)) {
                        ++p;
                    } else if (isBlank(ch)) {
                        ++p;
                        state = 9;
                    } else if (isE(ch)) {
                        ++p;
                        state = 6;
                    } else if (isDot(ch)) {
                        ++p;
                        state = 5;
                    } else {
                        return false;
                    }
                    break;
                case 4:
                    if (isDigit(ch)) {
                        ++p;
                        state = 5;
                    } else {
                        return false;
                    }
                    break;
                case 5:
                    if (isDigit(ch)) {
                        ++p;
                    } else if (isE(ch)) {
                        ++p;
                        state = 6;
                    } else if (isBlank(ch)) {
                        ++p;
                        state = 9;
                    } else {
                        return false;
                    }
                    break;
                case 6:
                    if (isSign(ch)) {
                        ++p;
                        state = 7;
                    } else if (isDigit(ch)) {
                        ++p;
                        state = 8;
                    } else {
                        return false;
                    }
                    break;
                case 7:
                    if (isDigit(ch)) {
                        ++p;
                        state = 8;
                    } else {
                        return false;
                    }
                    break;
                case 8:
                    if (isDigit(ch)) {
                        ++p;
                    } else if (isBlank(ch)) {
                        ++p;
                        state = 9;
                    } else {
                        return false;
                    }
                    break;
                case 9:
                    if (isBlank(ch)) {
                        ++p;
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return state == 3 || state == 5 || state == 8 || state == 9;
    }

}
