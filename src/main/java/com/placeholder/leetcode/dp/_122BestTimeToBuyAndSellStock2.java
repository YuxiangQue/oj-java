package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/8/3
 */
public class _122BestTimeToBuyAndSellStock2 {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int left = 0;
        int maxProfit = 0;
        while (left < prices.length) {
            int right = left;
            while (right + 1 < prices.length && prices[right + 1] >= prices[right]) {
                if (prices[right + 1] < prices[right]) {
                    break;
                }
                ++right;
            }
            maxProfit += prices[right] - prices[left];
            left = right + 1;
        }
        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertEquals(maxProfit(new int[]{}), 0);
        Assert.assertEquals(maxProfit(new int[]{1}), 0);
        Assert.assertEquals(maxProfit(new int[]{1, 1}), 0);
        Assert.assertEquals(maxProfit(new int[]{1, 2}), 1);
        Assert.assertEquals(maxProfit(new int[]{2, 1}), 0);
        Assert.assertEquals(maxProfit(new int[]{1, 2, 3}), 2);
        Assert.assertEquals(maxProfit(new int[]{3, 2, 3}), 1);
        Assert.assertEquals(maxProfit(new int[]{5, 1, 2, 3, 4, 0}), 3);
    }
}
