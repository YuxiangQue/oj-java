package com.placeholder.leetcode;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _338ContingBits {

    // f[i] = f[i/2] + i%2
    public static int[] countBits(int num) {
        int[] f = new int[num + 1];
        f[0] = 0;
        for (int i = 1; i <= num; ++i) {
            f[i] = f[i >> 1] + (i & 0x1);
        }
        return f;
    }
}
