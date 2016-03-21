package com.placeholder.leetcode.math;

/**
 * http://blog.csdn.net/m6830098/article/details/17291259
 * Created by yuxiangque on 2016/3/14.
 */
public class _31NextPermutation {

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1)
            return;

        // Step 1
        int partitionIndex = n - 1;
        boolean partitionIndexFound = false;
        for (; partitionIndex > 0; --partitionIndex) {
            if (nums[partitionIndex - 1] < nums[partitionIndex]) {
                partitionIndexFound = true;
                --partitionIndex;
                break;
            }
        }
        if (partitionIndexFound) {
            // Step 2
            boolean changeIndexFound = false;
            int changeIndex = n - 1;
            for (; changeIndex > partitionIndex; --changeIndex) {
                if (nums[changeIndex] > nums[partitionIndex]) {
                    changeIndexFound = true;
                    break;
                }
            }
            if (!changeIndexFound) {
                return;
            }
            // Step 3
            swap(nums, partitionIndex, changeIndex);
        } else {
            partitionIndex = -1;
        }

        // Step 4
        int left = partitionIndex + 1;
        int right = n - 1;
        int len = (right - left + 1) / 2;

        for (int i = 0; i < len; ++i) {
            swap(nums, left + i, right - i);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{6, 8, 7, 4, 3, 2};
        nextPermutation(arr1);
        int[] arr2 = new int[]{3, 2, 1};
        nextPermutation(arr2);
        return;
    }
}
