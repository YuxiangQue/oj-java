package com.placeholder.test.tencent2016;

import java.util.Scanner;


/**
 * 棋盘寻宝
 * http://www.acmerblog.com/blog-2-1240.html
 * map[i][j] += max(map[i-1][j], map[i][j-1])
 */
public class Tencent2016Campus {

    public static void dfs(int[][] m, int i, int j, int prevSum, int[] max) {
        if (max[0] < prevSum) {
            max[0] = prevSum;
        }
        if (j == m[0].length) {
            return;
        }
        if (i == m.length) {
            return;
        }
        prevSum += m[i][j];
        dfs(m, i + 1, j, prevSum, max); // 下
        dfs(m, i, j + 1, prevSum, max); // 右
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] tmp = new int[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    tmp[i][j] = scanner.nextInt();
                }
            }
            int[] max = new int[1];
            dfs(tmp, 0, 0, 0, max);
            System.out.println(max[0]);
        }
//        int[][] m = new int[][]{{0, 0, 8, 0, 0}, {0, 0, 0, 9, 0}, {0, 7, 0, 0, 0}, {0, 0, 6, 0, 0}};
//        int[] max = new int[1];
//        max[0] = Integer.MIN_VALUE;
//        dfs(m, 0, 0, 0, max);
//        System.out.println(max[0]);
//
//
//        m = new int[][]{{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
//        max = new int[1];
//        max[0] = Integer.MIN_VALUE;
//        dfs(m, 0, 0, 0, max);
//        System.out.println(max[0]);
    }
}
