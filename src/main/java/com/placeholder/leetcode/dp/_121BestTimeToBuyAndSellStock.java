package com.placeholder.leetcode.dp;

/**
 * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _121BestTimeToBuyAndSellStock {

    /**
     * _121BestTimeToBuyAndSellStock
     *
     * @param prices
     * @return
     */
    public int maxProfit_OneTransaction(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int minPrice = 0;
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
        }
        return maxProfit;
    }

}
