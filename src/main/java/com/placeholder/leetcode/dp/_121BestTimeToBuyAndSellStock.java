package com.placeholder.leetcode.dp;

/**
 * http://blog.csdn.net/linhuanmars/article/details/23162793
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _121BestTimeToBuyAndSellStock {

    //
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int runningMax = 0;
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            runningMax = Math.max(runningMax + prices[i + 1] - prices[i], 0);
            max = Math.max(runningMax, max);
        }
        return max;
    }
}
