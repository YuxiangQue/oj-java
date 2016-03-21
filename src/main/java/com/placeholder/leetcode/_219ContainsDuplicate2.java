package com.placeholder.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 阙宇翔
 * @version 2016/2/15
 */
public class _219ContainsDuplicate2 {
    /**
     * Time complexity: O(N), memory: O(N)
     *
     * @param nums
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        final Map<Integer, Integer> distinct = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (distinct.containsKey(nums[i]) && i - distinct.get(nums[i]) <= k) {
                return true;
            }
            distinct.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 4, 5, 1}, 3));
    }
}
