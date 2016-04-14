package com.placeholder.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _15ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // 升序  O(n*log(n))
        for (int i = 0; i < nums.length; ++i) {  // O(n2)
            int key = 0 - nums[i];
            // 搜索
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (nums[left] + nums[right] < key)
                    ++left;
                else if (nums[left] + nums[right] > key)
                    --right;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                ++i;
            }
        }
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> results = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        Assert.assertEquals(2, results.size());
        Assert.assertEquals(-1, (int) results.get(0).get(0));
        Assert.assertEquals(-1, (int) results.get(0).get(1));
        Assert.assertEquals(2, (int) results.get(0).get(2));
        Assert.assertEquals(-1, (int) results.get(1).get(0));
        Assert.assertEquals(0, (int) results.get(1).get(1));
        Assert.assertEquals(1, (int) results.get(1).get(2));

        results = threeSum(new int[]{-2, 0, 2});
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(-2, (int) results.get(0).get(0));
        Assert.assertEquals(0, (int) results.get(0).get(1));
        Assert.assertEquals(2, (int) results.get(0).get(2));


        results = threeSum(new int[]{-2, 0, 1, 1, 2});
        Assert.assertEquals(2, results.size());
        Assert.assertEquals(-2, (int) results.get(0).get(0));
        Assert.assertEquals(0, (int) results.get(0).get(1));
        Assert.assertEquals(2, (int) results.get(0).get(2));
        Assert.assertEquals(-2, (int) results.get(1).get(0));
        Assert.assertEquals(1, (int) results.get(1).get(1));
        Assert.assertEquals(1, (int) results.get(1).get(2));
    }
}
