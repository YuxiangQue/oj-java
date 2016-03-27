package com.placeholder.leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _1TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            numIndexMap.put(nums[index], index);
        }
        for (int i = 0; i < nums.length; i++) {
            if (numIndexMap.containsKey(target - nums[i])) {
                int index = numIndexMap.get(target - nums[i]);
                if (index != i) {
                    return new int[]{i, index};
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{0, 4, 3, 0}, 0)));
    }
}
