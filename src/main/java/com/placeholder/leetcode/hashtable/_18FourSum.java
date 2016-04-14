package com.placeholder.leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #Array
 * #HashTable
 * #TwoPointers
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _18FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // 升序  O(n*log(n))
        for (int i = 0; i < nums.length; ++i) {  // O(n2)
            for (int j = i + 1; j < nums.length; ++j) {
                int key = target - nums[i] - nums[j];
                // 搜索
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    if (nums[left] + nums[right] < key)
                        ++left;
                    else if (nums[left] + nums[right] > key)
                        --right;
                    else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 跳过相同数
                        while (left < right && nums[left] == nums[left + 1])
                            ++left;
                        // 跳过相同数
                        while (left < right && nums[right] == nums[right - 1])
                            --right;
                        ++left;
                        --right;
                    }
                }
                // 跳过相同数
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    ++j;
                }
            }
            // 跳过相同数
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                ++i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // [-2 0 2]
        List<List<Integer>> results = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }
}
