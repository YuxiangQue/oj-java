package com.placeholder.leetcode;

public class _42TrappingRainWater {
    public static int trap(int[] height) {
        if (height.length == 0)
            return 0;
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 0;
        right[n - 1] = 0;
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for (int j = n - 2; j >= 0; --j) {
            right[j] = Math.max(right[j + 1], height[j + 1]);
        }
        int totalTrap = 0;
        for (int i = 0; i < n; i++) {
            int trap = Math.min(left[i], right[i]) - height[i];
            if (trap > 0) {
                totalTrap += trap;
            }
        }
        return totalTrap;
    }

    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(heights));

        int[] height2 = {0};
        System.out.println(trap(height2));

        int[] height3 = {1, 2};
        System.out.println(trap(height3));

        int[] height4 = {2, 1, 2};
        System.out.println(trap(height4));
    }

}
