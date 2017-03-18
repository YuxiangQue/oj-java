package com.placeholder.leetcode;

public class _306AdditiveNumber {
    static boolean dfs(String num, long n2, long n1, int i, int count) {
        int n = num.length();
        if (i >= n) {
            return count >= 3;
        }
        if (num.charAt(i) == '0') { // leading zero
            if (count < 2 || 0 - n1 == n2) {
                if (dfs(num, n1, 0, i + 1, count + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            for (int j = i; j < n; ++j) {
                long n0 = 0;
                for (int k = i; k <= j; ++k) {
                    n0 = n0 * 10 + num.charAt(k) - '0';
                }
                if (n0 < 0) { // overflow
                    return false;
                }
                if (count < 2 || n0 - n1 == n2) {
                    if (dfs(num, n1, n0, j + 1, count + 1)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static boolean isAdditiveNumber(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    public static void main(String[] args) {
        System.out.println(dfs("", 0, 0, 0, 0));
        System.out.println(dfs("1", 0, 0, 0, 0));
        System.out.println(dfs("12", 0, 0, 0, 0));
        System.out.println(dfs("123", 0, 0, 0, 0));
        System.out.println(dfs("12315", 0, 0, 0, 0));
        System.out.println(dfs("112358", 0, 0, 0, 0));
        System.out.println(dfs("199100199", 0, 0, 0, 0)); // true
        System.out.println(dfs("19910011992", 0, 0, 0, 0)); // false
        System.out.println(dfs("1203", 0, 0, 0, 0)); // false
        System.out.println(dfs("101", 0, 0, 0, 0)); // true
        System.out.println(dfs("199001200", 0, 0, 0, 0)); // false
        System.out.println(dfs("000", 0, 0, 0, 0)); // true
        System.out.println(dfs("0000", 0, 0, 0, 0)); // true
    }
}
