package com.placeholder.leetcode.math;

/**
 * #Math #BitManipulation
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _231PowerOfTwo {
    boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
