package com.placeholder.leetcode;

import java.util.Arrays;

public class _495TeemoAttacking {

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        // Merge
        int n = timeSeries.length;
        long[] durations = new long[n];
        Arrays.fill(durations, duration);
        for (int i = 0, j = 1; j < n; ) {
            if (timeSeries[i] + durations[i] >= timeSeries[j]) {
                durations[i] = timeSeries[j] + durations[j] - timeSeries[i];
                durations[j] = 0;
                j += 1;
            } else {
                i = j;
                j += 1;
            }
        }
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += durations[i];
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        System.out.println(findPoisonedDuration(new int[]{0, 1, 2, 4, 8}, 2));
        System.out.println(findPoisonedDuration(new int[]{1, 4}, 2));
        System.out.println(findPoisonedDuration(new int[]{1, 2}, 2));
    }
}
