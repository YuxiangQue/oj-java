package com.placeholder.leetcode.dp;

/**
 * https://leetcode.com/problems/coin-change/
 */
public class _322CoinChange {
    public static int coinChangeSum(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];

        for (int i = 0; i < coins.length; ++i) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < amount + 1; ++j) {
            if (j - coins[0] >= 0)
                dp[0][j] += dp[0][j - coins[0]];
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0)
                    dp[i][j] += dp[i][j - coins[i]];
            }
        }
        return dp[coins.length - 1][amount];
    }

    public static int coinChangeMin(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];

        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < coins.length; ++i) {
            dp[i][0] = 0;
        }

        for (int j = 1; j < amount + 1; ++j) {
            dp[0][j] = -1;
            if (j - coins[0] >= 0 && dp[0][j - coins[0]] != -1)
                dp[0][j] = dp[0][j - coins[0]] + 1;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != -1) {
                    if (dp[i][j] == -1)
                        dp[i][j] = dp[i][j - coins[i]] + 1;
                    else
                        dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i][j]);
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChangeMin(new int[]{1, 3, 5}, 0));
        System.out.println(coinChangeMin(new int[]{2}, 1));
        System.out.println(coinChangeMin(new int[]{1, 2, 3}, 5));
        System.out.println(coinChangeMin(new int[]{1, 2, 5}, 11));
    }
}
