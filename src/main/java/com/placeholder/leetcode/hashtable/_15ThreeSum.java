package com.placeholder.leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _15ThreeSum {

    // https://leetcode.com/problems/3sum/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // 升序  O(n*log(n))
        for (int i = 0; i < nums.length; ++i) {  // O(n2)
            int key = 0 - nums[i];
            // 搜索
            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                if (nums[low] + nums[high] < key)
                    ++low;
                else if (nums[low] + nums[high] > key)
                    --high;
                else {
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    // 跳过相同数
                    while (low < high && nums[low] == nums[low + 1])
                        ++low;
                    // 跳过相同数
                    while (low < high && nums[high] == nums[high - 1])
                        --high;
                    ++low;
                    --high;
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

        List<List<Integer>> results = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> result : results) {
            System.out.println(result);
        }

        // [-2 0 2]
        results = threeSum(new int[]{-2, 0, 2});
        for (List<Integer> result : results) {
            System.out.println(result);
        }

        // [[-2,0,2],[-2,1,1]]
        results = threeSum(new int[]{-2, 0, 1, 1, 2});
        for (List<Integer> result : results) {
            System.out.println(result);
        }
    }
}
