package com.placeholder.leetcode.bitmanipulation;

/**
 * https://leetcode.com/problems/single-number-iii/
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _260SingleNumber3 {
    /**
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
     * exactly twice. Find the two elements that appear only once.
     * For example:
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
     * Note:
     * 1. The order of the result is not important. So in the above example, [5, 3] is also correct.
     * 2. Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
     *
     * @param nums
     * @return
     */
    public static int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int result = nums[0];

        // Use XOR to store the difference among these numbers,
        // if XOR all elements, the result is the difference between two number like result = 3^5
        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }

        // Find one digit 1 in the result, which can be used to distinguish 3 and 5.
        // depends on this, XOR elements will be equal to 3^result = 5 , 5^ result = 3
        int n = result & (~(result - 1));
        for (int num : nums) {
            if ((n & num) != 0) {
                res[0] = res[0] ^ num;
            } else {
                res[1] = res[1] ^ num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        singleNumber(new int[]{1, 2, 1, 3, 2, 5});
    }
}
