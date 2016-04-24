package com.placeholder.hihoCoder.weekly;

import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho95/problem/1
 *
 * @author yuxiangque
 * @version 2016/4/24
 */
public class _95ExtEuclid {

    // 2 追击 1，假设 1 静止，2用t步追上了1
    // (v2 - v1) * t - m * k = s1 - s2
    // 速度*次数-圈数*周长=距离
    // a*x + b*y = gcd(a,b)
    private static void extGcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return;
        }
        extGcd(b, a % b, x, y);
        long t = x[0];
        x[0] = y[0];
        y[0] = t - y[0] * (a / b);
    }

    private static long gcd(long a, long b) {
        if (a < b) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        while (b != 0) {
            long c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long s1 = scanner.nextInt();
        long s2 = scanner.nextInt();
        long v1 = scanner.nextInt();
        long v2 = scanner.nextInt();
        long m = scanner.nextInt();

        // (v2 - v1) * x - m * y = s1 - s2
        // (v2 - v1)' * x - m' * y = (s1-s2)'
        // (v2 - v1)' * x' - m' * y' = 1
        long a = (v2 - v1 + m) % m;
        long b = m;
        long c = (s1 - s2 + m) % m;

        long gcd = gcd(a, b);
        if (c % gcd != 0) {
            System.out.println(-1);
            return;
        }
        a = a / gcd;
        b = b / gcd;
        c = c / gcd;
        long[] x = new long[1];
        long[] y = new long[1];
        extGcd(a, b, x, y);   // a * x + b * y = 1
        x[0] = c * x[0] % b;
        if (x[0] < 0)
            x[0] += b;
        System.out.println(x[0]);
    }
}
