package com.placeholder.leetcode.dp;

public class _188BestTimeToBuyAndSellStockIV {
    /**
     * _188BestTimeToBuyAndSellStockIV
     *
     * @param prices
     * @return
     */
    public static int maxProfit_KTransaction(int k, int[] prices) {
        if (prices.length < 2) return 0;
        int n = prices.length;
        if (k >= n) return _122BestTimeToBuyAndSellStock2.maxProfit_InfTransaction(prices);

        // mustSell[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益
        int[][] mustSell = new int[n][k + 1];

        // // globalbest[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大
        int[][] globalBest = new int[n][k + 1];

        for (int i = 1; i < n; i++) {
            int profit = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                mustSell[i][j] = Math.max(globalBest[i - 1][j - 1], mustSell[i - 1][j] + profit);
                globalBest[i][j] = Math.max(globalBest[i - 1][j], mustSell[i][j]);
            }
        }
        return globalBest[n - 1][k];
    }

    public static void main(String[] args) {
        int max = maxProfit_KTransaction(1, new int[]{1, 2});
        System.out.println(max);
    }
}
