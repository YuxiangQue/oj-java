package com.placeholder.leetcode.bsearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/search-for-a-range/
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _34SearchForARange {


    public static void main(String[] args) {
        searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 8);
    }

    // 找到target后向两方向顺序搜索
    public static int[] searchRange(int[] nums, int target) {
        int lower = 0;
        int upper = nums.length - 1;
        int rangeLower;
        int rangeUpper;
        while (lower <= upper) {
            int middle = lower + (upper - lower) / 2;
            if (nums[middle] < target) {
                lower = middle + 1;
            } else if (nums[middle] > target) {
                upper = middle - 1;
            } else {
                rangeLower = rangeUpper = middle;
                while (rangeLower >= 0 && nums[rangeLower] == target)
                    --rangeLower;
                while (rangeUpper < nums.length && nums[rangeUpper] == target)
                    ++rangeUpper;
                return new int[]{rangeLower + 1, rangeUpper - 1};
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] searchRange2(int[] nums, int target) {
        int lower = 0;
        int upper = nums.length - 1;
        int rangeLower;
        int rangeUpper;
        // 找左边界
        while (lower < upper) {
            int middle = lower + (upper - lower) / 2;
            if (nums[middle] < target) {
                lower = middle + 1;
            } else if (nums[middle] > target) {
                upper = middle - 1;
            } else {
                upper = middle;
            }
        }
        if (nums[lower] != target)
            return new int[]{-1, -1};
        rangeLower = lower;

        // 下界不变
        upper = nums.length - 1;
        // 找右边界
        while (lower < upper) {
            int middle = lower + (upper - lower) / 2 + 1; // damn clever
            if (nums[middle] < target) {
                lower = middle + 1;
            } else if (nums[middle] > target) {
                upper = middle - 1;
            } else {
                lower = middle;
            }
        }
        if (nums[lower] != target)
            return new int[]{-1, -1};
        rangeUpper = lower;

        return new int[]{rangeLower, rangeUpper};
    }
}