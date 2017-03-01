package com.placeholder.leetcode.dp;

public class _123BestTimeToBuyAndSellStockIII {
    /**
     * _123BestTimeToBuyAndSellStockIII
     *
     * @param prices
     * @return
     */
    public static int maxProfit_TwoTransaaction(int[] prices) {
        if (prices.length < 2) return 0;

        int n = prices.length;
        int[] preProfit = new int[n];
        int[] postProfit = new int[n];

        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            preProfit[i] = Math.max(preProfit[i - 1], prices[i] - minPrice);
        }

        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            postProfit[i] = Math.max(postProfit[i + 1], maxPrice - prices[i]);
        }

        int maxProfit = 0;
        for (int i = 0; i < n; ++i) {
            maxProfit = Math.max(maxProfit, preProfit[i] + postProfit[i]);
        }

        return maxProfit;
    }
}
