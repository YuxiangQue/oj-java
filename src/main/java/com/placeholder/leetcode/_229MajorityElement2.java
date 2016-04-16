package com.placeholder.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * <p>
 * 多数投票算法
 *
 * @author yuxiangque
 * @version 2016/4/16
 * @see _169MajorityElement
 */
public class _229MajorityElement2 {

    // 默下来
    List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return Arrays.asList();
        if (nums.length == 1)
            return Arrays.asList(nums[0]);

        int n = nums.length;
        int counter1 = 0;
        int counter2 = 0;
        int candidate1 = 0;
        int candidate2 = 0;

        for (int num : nums) {
            if (candidate1 == num) {
                ++counter1;
            } else if (candidate2 == num) {
                ++counter2;
            } else if (counter1 == 0) {
                candidate1 = num;
                counter1 += 1;
            } else if (counter2 == 0) {
                candidate2 = num;
                counter2 += 1;
            } else {
                --counter1;
                --counter2;
            }
        }
        counter1 = 0;
        counter2 = 0;
        for (int i : nums) {
            if (i == candidate1)
                counter1++;
            if (i == candidate2)
                counter2++;
        }

        List<Integer> candidates = new ArrayList<>();
        if (counter1 > n / 3.0) {
            candidates.add(candidate1);
        }
        if (candidate2 != candidate1 && counter2 > n / 3.0) {
            candidates.add(candidate2);
        }
        return candidates;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, (int) majorityElement(new int[]{1}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{1}).get(0));

        Assert.assertEquals(1, (int) majorityElement(new int[]{1, 1}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{1, 1}).get(0));

        Assert.assertEquals(1, (int) majorityElement(new int[]{1, 1, 1, 2, 2, 2}).get(0));
        Assert.assertEquals(2, (int) majorityElement(new int[]{1, 1, 1, 2, 2, 2}).get(1));

        Assert.assertEquals(0, (int) majorityElement(new int[]{1, 2, 3}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{1, 1, 2, 3}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{1, 2, 2, 3}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{1, 2, 3, 3}).size());

        Assert.assertEquals(1, (int) majorityElement(new int[]{0}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{0, 0}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{0, 0, 0}).size());
        Assert.assertEquals(1, (int) majorityElement(new int[]{0, 0, 0, 0}).size());
    }
}
