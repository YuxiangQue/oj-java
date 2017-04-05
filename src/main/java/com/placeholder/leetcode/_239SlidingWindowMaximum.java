package com.placeholder.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _239SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] windows = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>(k);
        for (int i = 0; i < k; i++) {
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.offerLast(i);
        }
        windows[0] = nums[q.peekFirst()];

        for (int i = k; i < n; ++i) {
            while (!q.isEmpty() && q.peekFirst() <= i - k) {
                q.pollFirst();
            }
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.offerLast(i);
            windows[i - k + 1] = nums[q.peekFirst()];
        }
        return windows;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 1)));
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3}, 2)));
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 23, 123, 12, 31, 23, 123, 12, 3, 123, 1, 4, 24, 5, 435, 4567, 5, 8, 6, 786, 8, 678}, 4)));
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{4, 3, 11}, 3))); // 11
//        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3))); // 3 3 2 5
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5))); // 3 3 2 5
    }
}
