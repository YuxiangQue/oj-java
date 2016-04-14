package com.placeholder.leetcode.bitmanipulation;

/**
 * https://leetcode.com/problems/single-number/
 * #HashTable
 * #BitManipulation
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _136SingleNumber {

    /**
     * Given an array of integers, every element appears twice except for one. Find that single one.
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
