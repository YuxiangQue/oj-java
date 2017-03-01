package com.placeholder.leetcode.dp;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * http://bookshadow.com/weblog/2015/11/24/leetcode-best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class _309BestTimeToBuyAndSellStockWithCooldown {
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int n = prices.length;
        int[] sells = new int[n];
        int[] buys = new int[n];

        sells[0] = 0;
        buys[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            int profit = prices[i] - prices[i - 1];
            sells[i] = Math.max(buys[i - 1] + prices[i], sells[i - 1] + profit);
            buys[i] = buys[i - 1] - profit;
            if (i > 1)
                buys[i] = Math.max(sells[i - 2] - prices[i], buys[i]);
        }

        int maxProfit = sells[0];
        for (int sell : sells) {
            maxProfit = Math.max(sell, maxProfit);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
    }
}
