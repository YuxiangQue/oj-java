package com.placeholder.leetcode.math;

public class _390EliminationGame {
    static int f(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return 2 * g(n / 2);
    }

    static int g(int n) {
        return n + 1 - f(n);
    }

    public static int lastRemaining(int n) {
        return f(n);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 16; ++i) {
            System.out.println(lastRemaining(i));
        }
    }
}
