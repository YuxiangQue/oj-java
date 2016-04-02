package com.placeholder.leetcode.tree;

/**
 * https://leetcode.com/discuss/24282/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _96UniqueBinarySearchTree {


    /**
     * G(n) = F(1, n) + F(2, n) + ... + F(n, n)
     * F(i, n) = G(i-1) * G(n-i)
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
