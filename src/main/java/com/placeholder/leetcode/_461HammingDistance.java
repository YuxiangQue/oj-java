package com.placeholder.leetcode;

public class _461HammingDistance {

    public int hammingDistance(int x, int y) {
        return numOf1(x ^ y);
    }

    public int numOf1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count += 1;
        }
        return count;
    }
}
