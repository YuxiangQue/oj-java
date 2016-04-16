package com.placeholder.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/majority-element/
 * http://www.cnblogs.com/higerzhang/p/4181421.html
 * https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _169MajorityElement {


    /**
     * 排序
     * 时间：O(nlog(n))
     * 空间：O(1)
     *
     * @param nums
     * @return
     */
    int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 多数投票算法
     * http://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
     * 时间：O(n)
     * 空间：O(1)
     *
     * @param nums
     * @return
     */
    int majorityElement1(int[] nums) {
        int n = nums.length;
        int counter = 0;
        int candidate = nums[0];
        for (int num : nums) {
            if (counter == 0) {
                candidate = num;
                counter += 1;
            } else {
                if (candidate == num) {
                    ++counter;
                } else {
                    --counter;
                }
            }
        }
        counter = 0;
        for (int i : nums) {
            if (i == candidate)
                counter++;
        }
        if (counter < (n + 1) / 2)
            return -1;
        return candidate;
    }

    /**
     * map
     * 时间：O(n)
     * 空间：O(n)
     *
     * @param nums
     * @return
     */
    int majorityElement(int[] nums) {
        int majority = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            int count = map.get(num) + 1;
            map.put(num, count);
            if (count > majority) {
                return num;
            }
        }
        return -1;
    }
}
