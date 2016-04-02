package com.placeholder.leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths/
 *
 * @author yuxiangque
 * @version 2016/4/2
 */
public class _62UniquePaths {
    int uniquePaths(int m, int n) {
        int[][] tmp = new int[m][n];

        // fill row
        Arrays.fill(tmp[0], 1);

        // fill column
        for (int i = 0; i < m; ++i) {
            tmp[i][0] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                tmp[i][j] = tmp[i - 1][j] + tmp[i][j - 1];
            }
        }
        return tmp[m - 1][n - 1];
    }
}
