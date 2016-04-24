package com.placeholder.hihoCoder.weekly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho94/problem/1
 * http://www.cnblogs.com/void/archive/2011/04/21/2024377.html
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _94Josephus {


    /**
     * n个人（编号1~n)，从1开始报数，报到m的退出，剩下的人继续从1开始报数。求胜利者的编号
     * 时间复杂度：O(n)
     *
     * @param n 人数
     * @param m 出圈步长
     * @return
     */
    public static int yosephus1(int n, int m) {
        int s = 0;
        for (int i = 2; i <= n; i++) {
            s = (s + m) % i;
        }
        return s + 1;
    }


    /**
     * 时间复杂度：O(m)
     *
     * @param n 人数
     * @param m 出圈步长
     * @param k 使报数位置
     * @return
     */
    public static long yoseplus2(long n, long m, long k) {
        if (m == 1)
            k = k == 1 ? n : (k + n - 1) % n;
        else {
            for (long i = 1; i <= n; i++) {
                if ((k + m) < i) {
                    long x = (i - k + 1) / (m - 1) - 1;
                    if (i + x < n) {
                        i = i + x;
                        k = (k + m * x);
                    } else {
                        k = k + m * (n - i);
                        i = n;
                    }
                }
                k = (k + m - 1) % i + 1;
            }
        }
        return k; //返回最后一人的位置
    }

    /**
     * n个人（编号1~n)，从1开始报数，报到m的退出，剩下的人继续从1开始报数。按顺序输出列者编号
     *
     * @param n 人数
     * @param m 出圈步长
     * @return
     */
    public static List<Integer> yosephusSeq(int n, int m) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int p;
        while (++i <= n) {
            p = i * m;
            while (p > n)
                p = p - n + (p - n - 1) / (m - 1);
            result.add(p);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println(yoseplus2(scanner.nextLong(), scanner.nextLong(), 1) - 1);
        }
    }
}
