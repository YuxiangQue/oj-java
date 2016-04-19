package com.placeholder.common;

/**
 * 数论
 * @author yuxiangque
 * @version 2016/3/22
 */
public class NumberTheory {

    public static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    // 质数大于等于2 不能被它本身和1以外的数整除
    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i < (int) (Math.sqrt(n) + 1); ++i) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
