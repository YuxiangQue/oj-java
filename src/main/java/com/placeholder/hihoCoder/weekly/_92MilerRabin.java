package com.placeholder.hihoCoder.weekly;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho92/problems 数论一·Miller-Rabin质数测试
 * http://www.cnblogs.com/vongang/archive/2012/03/15/2398626.html
 *
 * @author yuxiangque
 * @version 2016/4/6
 */
public class _92MilerRabin {

    // a * b % n
    private static long modMul(long a, long b, long n) {
        long res = 0;
        while (b != 0) {
            if ((b & 1) != 0)
                res = (res + a) % n;
            a = (a + a) % n;
            b >>= 1;
        }
        return res;
    }

    // a^b % n
    // 同理
    private static long modExp(long a, long b, long n) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) != 0) res = modMul(res, a, n);
            a = modMul(a, a, n);
            b >>= 1;
        }
        return res;
    }

    static boolean millerRabin(long n) {
        if (n <= 1)
            return false;
        if (n == 2 || n == 3 || n == 5 || n == 7 || n == 11) return true;
        if (n == 1 || n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0) return false;

        long x, pre, u;
        int i, j, k = 0;
        u = n - 1;    //要求x^u % n

        while ((u & 1) == 0) {    //如果u为偶数则u右移，用k记录移位数
            k++;
            u >>= 1;
        }

        // 判断错误概率为(1/4)^S
        int S = 5;
        Random random = new Random();
        for (i = 0; i < S; ++i) {    //进行S次测试
            x = 2 + (long) (random.nextDouble() * (n - 2));    //在[2, n)中取随机数
            if ((x % n) == 0) continue;

            x = modExp(x, u, n);    //先计算(x^u) % n，
            pre = x;
            for (j = 0; j < k; ++j) {    //把移位减掉的量补上，并在这地方加上二次探测
                x = modMul(x, x, n);
                if (x == 1 && pre != 1 && pre != n - 1) return false;    //二次探测定理，这里如果x = 1则pre 必须等于 1，或则 n-1否则可以判断不是素数
                pre = x;
            }
            if (x != 1) return false;    //费马小定理
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println(millerRabin(scanner.nextLong()) ? "Yes" : "No");
        }
    }

    @Test
    public void test() {
        Random random = new Random();
        for (int i = 0; i < 1500000; i++) {
            millerRabin((long) (random.nextLong() % 10E18));
        }
    }
}
