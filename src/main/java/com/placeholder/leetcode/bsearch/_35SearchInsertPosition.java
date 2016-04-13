package com.placeholder.leetcode.bsearch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/search-insert-position/
 * <p>
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _35SearchInsertPosition {

    // duplicates
    public static int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle;
            } else {
                right = middle;
            }
        }
        return left;
    }

    // no duplicates in the array
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return left;
    }

    @Test
    public void test() {
        assertEquals(2, searchInsert(new int[]{1, 3, 5, 6}, 5));
        assertEquals(1, searchInsert(new int[]{1, 3, 5, 6}, 2));
        assertEquals(4, searchInsert(new int[]{1, 3, 5, 6}, 7));
        assertEquals(0, searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    @Test
    public void testSearchInsert2() {
        assertEquals(0, searchInsert2(new int[]{1, 3, 3, 6}, 0));
        assertEquals(0, searchInsert2(new int[]{1, 3, 3, 6}, 1));
        assertEquals(1, searchInsert2(new int[]{1, 3, 3, 6}, 2));
        assertEquals(1, searchInsert2(new int[]{1, 3, 3, 6}, 3));
        assertEquals(1, searchInsert2(new int[]{1, 3, 3, 6}, 2));
        assertEquals(3, searchInsert2(new int[]{1, 3, 3, 6}, 6));

        assertEquals(0, searchInsert2(new int[]{1, 3, 3, 6, 6}, 0));
        assertEquals(0, searchInsert2(new int[]{1, 3, 3, 6, 6}, 1));
        assertEquals(1, searchInsert2(new int[]{1, 3, 3, 6, 6}, 2));
        assertEquals(1, searchInsert2(new int[]{1, 3, 3, 6, 6}, 3));
        assertEquals(1, searchInsert2(new int[]{1, 3, 3, 6, 6}, 2));
        assertEquals(3, searchInsert2(new int[]{1, 3, 3, 6, 6}, 6));
    }
}
