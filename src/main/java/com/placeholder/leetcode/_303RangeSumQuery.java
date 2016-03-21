package com.placeholder.leetcode;

/**
 * @author 阙宇翔
 * @version 2016/2/24
 */
public class _303RangeSumQuery {

    public static void main(String[] args) {
        System.out.println(new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 2));
        System.out.println(new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(2, 5));
        System.out.println(new NumArray(new int[]{-2, 0, 3, -5, 2, -1}).sumRange(0, 5));
    }

    static public class NumArray {

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
            return;
        }

        public int sumRange(int i, int j) {
            if (i >= 0 && i <= j) {
                return sums[j + 1] - sums[i];
            }
            return 0;
        }
    }

}
