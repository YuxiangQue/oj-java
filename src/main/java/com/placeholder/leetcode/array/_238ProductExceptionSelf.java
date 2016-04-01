package com.placeholder.leetcode.array;

/**
 * @author yuxiangque
 * @version 2016/4/1
 */
public class _238ProductExceptionSelf {
    int[] productExceptSelf(int[] nums) {
        int size = nums.length;
        int[] right = new int[size];
        right[size - 1] = 1;   // right[i] = nums[i+1]*...nums[n-1]
        for (int i = size - 1; i > 0; --i)
            right[i - 1] = right[i] * nums[i];
        int left = 1;
        for (int i = 0; i < size; ++i) {
            right[i] = left * right[i];
            left = left * nums[i];
        }
        return right;
    }
}
