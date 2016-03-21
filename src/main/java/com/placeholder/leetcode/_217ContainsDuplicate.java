package com.placeholder.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 阙宇翔
 * @version 2016/2/15
 */
public class _217ContainsDuplicate {

    /**
     * Time complexity: O(N), memory: O(N)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {

        final Set<Integer> distinct = new HashSet<>();
        for (int num : nums) {
            if (distinct.contains(num)) {
                return true;
            }
            distinct.add(num);
        }
        return false;
    }

    /**
     * Time complexity: O(N lg N), memory: O(1)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate1(int[] nums) {

        Arrays.sort(nums);
        for (int ind = 1; ind < nums.length; ind++) {
            if (nums[ind] == nums[ind - 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Time complexity: O(N^2), memory: O(1)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
