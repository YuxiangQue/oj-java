package com.placeholder.mstest2015sept2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/mstest2015sept2/problem/2
 * https://zh.wikipedia.org/wiki/Floyd-Warshall%E7%AE%97%E6%B3%95
 *
 * @author yuxiangque
 * @version 2016/4/12
 */
public class TotalHighwayDistance {

    public static int THD(short[][] highway) {
        int N = highway.length;
        short[][] temp = new short[N][N];  // 1E10
        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(highway[i], N);
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp[i][j] = (short) Math.min(temp[i][j], temp[i][k] + temp[k][j]);
                }
            }
        }
        int thd = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                thd += temp[i][j];
            }
        }
        return thd;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        short[][] highway = new short[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                highway[i][j] = 10000;  // 0 <= k <= 1000
            }
            highway[i][i] = 0;
        }
        for (int i = 1; i < N; i++) {
            int city1 = scanner.nextInt() - 1;
            int city2 = scanner.nextInt() - 1;
            short distance = scanner.nextShort();
            highway[city1][city2] = distance;
            highway[city2][city1] = distance;
        }
        for (int i = 0; i < M; i++) {
            String command = scanner.next();
            if (Objects.equals("QUERY", command)) {
                System.out.println(THD(highway));
            } else if (Objects.equals("EDIT", command)) {
                int city1 = scanner.nextInt() - 1;
                int city2 = scanner.nextInt() - 1;
                short distance = scanner.nextShort();  // 0 <= k <= 1000
                highway[city1][city2] = distance;
                highway[city2][city1] = distance;
            }
        }
    }
}
