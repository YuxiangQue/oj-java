package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/8/3
 */
public class _122BestTimeToBuyAndSellStock2 {

    /**
     * _122BestTimeToBuyAndSellStockII
     *
     * @param prices
     * @return
     */
    public static int maxProfit_InfTransaction(int[] prices) {
        if (prices.length < 2) return 0;
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if (profit > 0) {
                maxProfit += profit;
            }
        }
        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{}), 0);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{1}), 0);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{1, 1}), 0);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{1, 2}), 1);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{2, 1}), 0);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{1, 2, 3}), 2);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{3, 2, 3}), 1);
        Assert.assertEquals(maxProfit_InfTransaction(new int[]{5, 1, 2, 3, 4, 0}), 3);
    }
}
