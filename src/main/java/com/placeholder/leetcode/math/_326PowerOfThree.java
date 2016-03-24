package com.placeholder.leetcode.math;

/**
 * #Math
 * https://leetcode.com/discuss/78532/summary-all-solutions-new-method-included-at-15-30pm-jan-8th
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _326PowerOfThree {
    /**
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        return n > 0 && (1162261467 % n == 0);
    }
}
