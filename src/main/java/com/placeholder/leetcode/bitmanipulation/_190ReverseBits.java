package com.placeholder.leetcode.bitmanipulation;

/**
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _190ReverseBits {
    int swapBit(int n, int i, int j) {
        if (((n >> i & 0x01) ^ (n >> j & 0x01)) != 0)
            n ^= (1 << i) | (1 << j);
        return n;
    }

    int reverseBits(int n) {
        for (int i = 0; i < 16; ++i) {
            n = swapBit(n, i, 32 - i - 1);
        }
        return n;
    }
}
