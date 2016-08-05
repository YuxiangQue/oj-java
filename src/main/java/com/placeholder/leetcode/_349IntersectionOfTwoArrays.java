package com.placeholder.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuxiangque
 * @version 2016/8/4
 */
public class _349IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> temp = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            int num1 = nums1[i], num2 = nums2[j];
            if (num1 == num2) {
                temp.add(nums1[i]);
                while (i < nums1.length && nums1[i] == num1) ++i;
                while (j < nums2.length && nums2[j] == num2) ++j;
            } else if (num1 > num2) {
                while (j < nums2.length && num1 > nums2[j]) ++j;
            } else {
                while (i < nums1.length && nums1[i] < num2) ++i;
            }
        }
        int[] answer = new int[temp.size()];
        for (int idx = 0; idx < temp.size(); idx++) {
            answer[idx] = temp.get(idx);
        }
        return answer;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{}, intersection(new int[]{}, new int[]{}));
        Assert.assertArrayEquals(new int[]{1}, intersection(new int[]{1}, new int[]{1}));
        Assert.assertArrayEquals(new int[]{1}, intersection(new int[]{1, 2}, new int[]{1}));
        Assert.assertArrayEquals(new int[]{2}, intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
    }
}
