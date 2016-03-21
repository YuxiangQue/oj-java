package com.placeholder.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _228SummaryRanges {

    public static void main(String[] args) {
        System.out.println(new _228SummaryRanges().summaryRanges(null));
        System.out.println(new _228SummaryRanges().summaryRanges(new int[]{}));
        System.out.println(new _228SummaryRanges().summaryRanges(new int[]{0}));
        System.out.println(new _228SummaryRanges().summaryRanges(new int[]{0, 1, 3}));
        System.out.println(new _228SummaryRanges().summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
    }

    /**
     * https://leetcode.com/problems/summary-ranges/
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return summary;
        for (int i = 0; i < nums.length; ) {
            int j = i;
            while (j + 1 < nums.length && nums[j] + 1 == nums[j + 1]) {
                ++j;
            }
            if (i == j) {
                summary.add(nums[i] + "");
            } else {
                summary.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
        }
        return summary;
    }

}
