package com.placeholder.leetcode.math;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _172TrailingZeros {
    public static void main(String[] args) {
        System.out.println(new _172TrailingZeros().tailingZeros3(5));      // 1
        System.out.println(new _172TrailingZeros().tailingZeros3(10));     // 2
        System.out.println(new _172TrailingZeros().tailingZeros3(15));     // 3
        System.out.println(new _172TrailingZeros().tailingZeros3(20));     // 4
        System.out.println(new _172TrailingZeros().tailingZeros3(796));    // 197
        System.out.println(new _172TrailingZeros().tailingZeros3(65536));  // 16380
        System.out.println(new _172TrailingZeros().tailingZeros3(1808548329));  // 452137076
    }

    /**
     * http://blog.csdn.net/yahohi/article/details/7528803
     * Given an integer n, return the number of trailing zeroes in n!.
     * Note: Your solution should be in logarithmic time complexity.
     *
     * @param n
     * @return
     */
    public void trailingZeroes(int n, int[] numZeros) {
        if (0 <= n && n < 5) {
            numZeros[0] += 0;
            return;
        }
        numZeros[0] += n / 5;
        trailingZeroes(n / 5, numZeros);
    }

    // 尾递归优化版本
    public int trailingZeroes2(int n) {
        int[] numZeros = new int[1];
        trailingZeroes(n, numZeros);
        return numZeros[0];
    }

    // 递归版本
    public int trailingZeroes1(int n) {
        if (0 <= n && n < 5) {
            return 0;
        }
        return n / 5 + trailingZeroes(n / 5);
    }

    // 循环版本
    public int tailingZeros3(int n) {
        int numZeros = 0;
        for (int i = n; i >= 5; i /= 5) {
            numZeros += i / 5;
        }
        return numZeros;
    }

    public int trailingZeroes(int n) {
        int total = 0;
        for (int i = 0; i <= n; i += 5) {
            int num = i;

            int count = 0;
            while (num != 0 && num % 5 == 0) {
                ++count;
                num /= 5;
            }
            total += count;
        }
        return total;
    }
}
