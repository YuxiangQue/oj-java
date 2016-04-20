package com.placeholder.jianzhioffer;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author yuxiangque
 * @version 2016/4/20
 */
public class PrintMatrix {
    // 1 2 3 4 5
    // 6 7 8 9 10
    // 11 12 13 14 15
    // 16 17 18 19 20
    // 21 22 23 24 25
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<Integer>();
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int levels = Math.min(m, n) / 2;
        ArrayList<Integer> result = new ArrayList<Integer>();
        int level = 0;
        for (; level < levels; level++) {
            for (int j = level; j < n - level - 1; ++j) {
                result.add(matrix[level][j]);
            }
            for (int i = level; i < m - level - 1; ++i) {
                result.add(matrix[i][n - level - 1]);
            }
            for (int j = n - level - 1; j > level; --j) {
                result.add(matrix[m - level - 1][j]);
            }
            for (int i = m - level - 1; i > level; --i) {
                result.add(matrix[i][level]);
            }
        }
        if (m >= n && n % 2 == 1) {
            for (int i = level; i <= m - level - 1; ++i) {
                result.add(matrix[i][n - level - 1]);
            }
        } else if (m < n && m % 2 == 1) {
            for (int j = level; j <= n - level - 1; ++j) {
                result.add(matrix[level][j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(printMatrix(new int[][]{{1}, {2}, {3}, {4}, {5}}));  // 1 2 3 6 8 8 7 4 5

        System.out.println(printMatrix(new int[][]{}));
        System.out.println(printMatrix(new int[][]{{1}}));
        System.out.println(printMatrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));  // 1 2 3 6 8 8 7 4 5

        System.out.println(printMatrix(new int[][]{{1, 2}, {3, 4}}));
        System.out.println(printMatrix(new int[][]{{1, 2, 4, 5}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}));


    }
}
