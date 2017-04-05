package com.placeholder.leetcode;

import java.util.Arrays;

public class _164MaximumGap {

    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{1, 3, 9}));
        System.out.println(maximumGap(new int[]{}));
        System.out.println(maximumGap(new int[]{1, 2}));
        System.out.println(maximumGap(new int[]{1, 1, 1, 1}));
    }

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int max = nums[0], min = nums[0];
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        if (max == min) {
            return 0;
        }

        int gap = Math.max(1, (max - min) / (nums.length - 1)); // bucket gap
        int bucketCount = (max - min) / gap + 1;

        int[] minBuckets = new int[bucketCount];
        int[] maxBuckets = new int[bucketCount];

        Arrays.fill(minBuckets, Integer.MAX_VALUE);
        Arrays.fill(maxBuckets, Integer.MIN_VALUE);

        for (int num : nums) {
            int bucketIdx = (num - min) / gap;
            if (maxBuckets[bucketIdx] < num) {
                maxBuckets[bucketIdx] = num;
            }
            if (num < minBuckets[bucketIdx]) {
                minBuckets[bucketIdx] = num;
            }
        }

        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < bucketCount; i++) {
            if (minBuckets[i] == Integer.MAX_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, minBuckets[i] - previous);
            previous = maxBuckets[i];
        }
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;
    }
}
