package com.placeholder.leetcode.tree;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _96UniqueBinarySearchTree {


    /**
     * @param n
     * @return
     */
    int numTrees(int n) {
        int[] num = new int[n + 1];
        for (int i = 0; i < n + 1; ++i)
            num[i] = 0;
        num[0] = num[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                num[i] += num[j - 1] * num[i - j];
            }
        }
        return num[n];
    }
}
