package com.placeholder.leetcode.math;

/**
 * http://blog.csdn.net/s634772208/article/details/46505949
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _43MultiplyStrings {


    /**
     * @param a 123 即 [3, 2, 1]
     * @param b
     * @return
     */
    private static int[] add(int[] a, int[] b) {
        int[] shortString;
        int[] longString;
        int shortLength;
        int longLength;
        if (a.length < b.length) {
            shortString = a;
            longString = b;
            shortLength = a.length;
            longLength = b.length;
        } else {
            shortString = b;
            longString = a;
            shortLength = b.length;
            longLength = a.length;
        }
        int[] c = new int[longLength + 1];
        int carry = 0;
        for (int i = 0; i < shortLength; ++i) {
            int a1 = shortString[i];
            int a2 = longString[i];
            c[i] = (carry + a1 + a2) % 10;
            carry = (carry + a1 + a2) / 10;
        }
        for (int i = shortLength; i < longLength; ++i) {
            int a2 = longString[i];
            c[i] = (carry + a2) % 10;
            carry = (carry + a2) / 10;
        }
        if (carry == 1) {  // 剩余的进位
            c[longLength] = 1;
        }
        return c;
    }

    private static int[] multiply(int[] num1, int[] num2) {
        int length1 = num1.length;
        int length2 = num2.length;
        int[] result = new int[length2 + length1 + 1];
        for (int i = 0; i < length1; ++i) { //
            int a1 = num1[i];  // 乘数
            int[] tmp = new int[length2 + length1 + 1];  // 保存位运算结果
            int carry = 0;
            for (int j = 0; j < length2; ++j) {
                int a2 = num2[j]; // 被乘数
                int tmp1 = carry + a1 * a2;
                tmp[j + i] = tmp1 % 10;
                carry = tmp1 / 10;
            }
            if (carry != 0) {
                tmp[length2 + i] = carry;
            }
            result = add(result, tmp);
        }
        return result;
    }

    // https://leetcode.com/problems/multiply-strings/
    public static String multiply(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int[] num11 = new int[length1];
        int[] num22 = new int[length2];
        for (int i = 0; i < length1; ++i) {
            num11[i] = num1.charAt(length1 - 1 - i) - '0';
        }
        for (int i = 0; i < length2; ++i) {
            num22[i] = num2.charAt(length2 - 1 - i) - '0';
        }
        int[] result = multiply(num11, num22);
        int ignore = result.length - 1;
        StringBuilder sb = new StringBuilder();
        while (ignore >= 0 && result[ignore] == 0) // 跳过多余的0
            --ignore;
        for (int i = ignore; i >= 0; --i) {
            sb.append(result[i]);
        }
        if (sb.length() == 0) // 空算0
            sb.append("0");
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = multiply("123", "12");
        s1 = multiply("12", "123");
        s1 = multiply("12", "0");
        s1 = multiply("9", "9");
        s1 = multiply("156498432342", "1616523424");
        return;
    }
}
