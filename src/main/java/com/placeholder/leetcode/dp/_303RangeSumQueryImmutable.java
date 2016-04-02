package com.placeholder.leetcode.dp;

/**
 * @author yuxiangque
 * @version 2016/4/2
 */
public class _303RangeSumQueryImmutable {
    public class NumArray {

        int[] nums;
        int[] sums;

        public NumArray(int[] nums) {
            this.nums = nums;
            sums = new int[nums.length + 1];
            sums[0] = 0;
            if (nums.length >= 1) {
                sums[1] = nums[0];
                for (int i = 1; i < nums.length; ++i) {
                    sums[i + 1] = sums[i] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            if (i >= 0 && i <= j) {
                return sums[j + 1] - sums[i];
            }
            return 0;
        }
    }
}
