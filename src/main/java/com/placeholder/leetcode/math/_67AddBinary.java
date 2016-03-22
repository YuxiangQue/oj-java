package com.placeholder.leetcode.math;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _67AddBinary {
    public static String addBinary(String a, String b) {
        String shortString;
        String longString;
        int shortLength;
        int longLength;
        if (a.length() < b.length()) {
            shortString = a;
            longString = b;
            shortLength = a.length();
            longLength = b.length();
        } else {
            shortString = b;
            longString = a;
            shortLength = b.length();
            longLength = a.length();
        }
        int[] c = new int[longLength + 1];
        int carry = 0;
        for (int i = 0; i < shortLength; ++i) {
            int a1 = shortString.charAt(shortLength - 1 - i) - '0';
            int a2 = longString.charAt(longLength - 1 - i) - '0';
            c[i] = (carry + a1 + a2) % 2;
            carry = (carry + a1 + a2) / 2;
        }
        for (int i = shortLength; i < longLength; ++i) {
            int a2 = longString.charAt(longLength - 1 - i) - '0';
            c[i] = (carry + a2) % 2;
            carry = (carry + a2) / 2;
        }
        if (carry == 1) {  // 剩余的进位
            c[longLength] = 1;
        }
        StringBuilder sb = new StringBuilder();
        while (longLength >= 0 && c[longLength] == 0) // 跳过多余的0
            --longLength;
        for (int i = longLength; i >= 0; --i) {
            sb.append(c[i]);
        }
        if (sb.length() == 0) // 空算0
            sb.append("0");
        return sb.toString();
    }

    public static void main(String[] args) {
        addBinary("101111", "10");
        System.out.println(addBinary("0", "0"));
        addBinary("0", "1");
        addBinary("10", "10");
        addBinary("10", "11");
        addBinary("11", "1");
    }
}
