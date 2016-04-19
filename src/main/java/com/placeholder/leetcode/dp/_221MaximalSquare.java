package com.placeholder.leetcode.dp;

import com.placeholder.common.Common;

/**
 * https://leetcode.com/problems/maximal-square/
 * https://www.youtube.com/watch?v=_Lf1looyJMU&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=27
 *
 * @author yuxiangque
 * @version 2016/4/2
 */
public class _221MaximalSquare {


    //
    // dp[i][j] = 0, if matrix[i][j] == 0
    // dp[i][j] = min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+1)
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int t = Math.min(dp[i - 1][j], dp[i][j - 1]);
                t = Math.min(t, dp[i - 1][j - 1]);
                dp[i][j] = t + 1;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max * max;
    }


    public static void main(String[] args) {
        int result = maximalSquare(Common.stringArrayToCharArray2(new String[]{"01101", "11100", "11110", "11101"}));
        System.out.println(result);

        result = maximalSquare(Common.stringArrayToCharArray2(new String[]{"1"}));
        System.out.println(result);
    }
}
