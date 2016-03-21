package com.placeholder.leetcode.bitmanipulation;

/**
 * https://leetcode.com/problems/single-number-ii/
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _137SingleNumber2 {

    /**
     * Given an array of integers, every element appears three times except for one. Find that single one.
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        final int INT_SIZE = 32 * 8;
        int[] bitCount = new int[INT_SIZE];

        for (int num : nums)
            for (int j = 0; j < INT_SIZE; ++j)
                bitCount[j] += ((num & (1 << j)) != 0) ? 1 : 0;

        int single = 0;
        for (int j = 0; j < INT_SIZE; ++j)
            if ((bitCount[j] % 3) == 1)
                single += (1 << j);
        return single;
    }
}
