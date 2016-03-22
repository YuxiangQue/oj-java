package com.placeholder.leetcode.dp;

/**
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class _70ClimbingStairs {
    // S[n] = S[n-1] + S[n-2]
    // S[1] = 1
    // S[2] = 2
    int climbStairs(int n) {
        int sn = 0, sn1 = 2, sn2 = 1;
        if (n == 1)
            return sn2;
        if (n == 2)
            return sn1;
        for (int i = 2; i < n; ++i) {
            sn = sn2 + sn1;
            sn2 = sn1;
            sn1 = sn;
        }
        return sn;
    }
}
