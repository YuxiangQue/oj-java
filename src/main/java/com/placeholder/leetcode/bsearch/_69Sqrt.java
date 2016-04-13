package com.placeholder.leetcode.bsearch;

/**
 * #Math
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/4/13
 */
public class _69Sqrt {

    // y = x^2 - a^2
    // y' = 2*x

    // x2 = x1 - f(x1)/f'(x1)
    // x2 = x1 - (x1^2-a^2)/(2*x1)
    public int mySqrt(int a) {
        if (a == 0)
            return 0;
        double eps = 1E-4;
        double x1 = a;
        while (true) {
            x1 = (x1 + a / x1) * 0.5;
            if (Math.abs(x1 * x1 - a) < eps)
                break;
        }
        return (int) x1;
    }
}
