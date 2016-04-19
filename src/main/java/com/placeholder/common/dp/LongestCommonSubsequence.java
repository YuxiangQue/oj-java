package com.placeholder.common.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 *
 * @author yuxiangque
 * @version 2016/4/17
 */
public class LongestCommonSubsequence {

    // dp[i][j] = 0, If i == 0 And j == 0
    // dp[i][j] = dp[i-1][j-1] + 1, If x[i] == y[i]
    // dp[i][j] = max{dp[i][j-1], dp[i-1][j]}, Else
    private static int[][] dp(char[] input1, char[] input2) {
        int m = input1.length;
        int n = input2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (input1[i - 1] == input2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp;
    }

    // LCS 长度
    public static int length(String x, String y) {
        return dp(x.toCharArray(), y.toCharArray())[x.length()][y.length()];
    }

    // 找出一个LCS
    // https://www.ics.uci.edu/~eppstein/161/960229.html
    public static String one(String x, String y) {
        char[] input1 = x.toCharArray();
        char[] input2 = y.toCharArray();
        int[][] dp = dp(input1, input2);

        int m = x.length();
        int n = y.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (input1[i] == input2[j]) {
                sb.append(input1[i]);
                ++i;
                ++j;
            } else if (dp[i + 1][j] >= dp[i][j + 1]) {
                ++i;
            } else {
                ++j;
            }
        }
        return sb.toString();
    }

    private static List<String> backtrackAll(int[][] C, char[] X, char[] Y, int i, int j) {
        if (i == 0 || j == 0) {
            return Collections.singletonList("");
        } else if (X[i - 1] == Y[j - 1]) {
            return backtrackAll(C, X, Y, i - 1, j - 1).stream().map(each -> each + X[i - 1]).collect(Collectors.toList());
        } else {
            List<String> R = new ArrayList<>();
            if (C[i - 1][j] >= C[i][j - 1]) {
                R.addAll(backtrackAll(C, X, Y, i - 1, j));
            }
            if (C[i][j - 1] >= C[i - 1][j]) {
                R.addAll(backtrackAll(C, X, Y, i, j - 1));
            }
            return R;
        }
    }

    // 回溯获取所有的LCS
    // https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
    public static List<String> backtrackAll(String x, String y) {
        char[] input1 = x.toCharArray();
        char[] input2 = y.toCharArray();
        int[][] dp = dp(input1, input2);
        return backtrackAll(dp, input1, input2, x.length(), y.length());
    }

    public static void main(String[] args) {
        System.out.println(backtrackAll("abcdefgh", "abcedddfgh"));
    }
}
