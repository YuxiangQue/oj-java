package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * @author yuxiangque
 * @version 2016/4/5
 */
public class _84LargestRectangleInHistogram {


    // O(n)
    public static int largestRectangleArea(int[] heights) {
        if (heights.length == 0)
            return 0;

        int length = heights.length;

        // 向右能延伸多远
        int[] right = new int[length];
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; --i) {
            if (heights[i] > heights[i + 1]) {
                right[i] = 1;
            } else {
                int j = i + 1;
                while (j < length && heights[j] >= heights[i]) {
                    j += right[j];
                }
                right[i] = j - i;
            }
        }

        // 向左能延伸多远
        int[] left = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; ++i) {
            if (heights[i] > heights[i - 1]) {
                left[i] = 1;
            } else {
                int j = i - 1;
                while (j >= 0 && heights[j] >= heights[i]) {
                    j -= left[j];
                }
                left[i] = i - j;
            }
        }

        // 求面积
        int maxArea = heights[0];
        for (int i = 0; i < length; i++) {
            maxArea = Math.max(heights[i] * (left[i] + right[i] - 1), maxArea);
        }
        return maxArea;
    }

    @Test
    public void test() {
        Assert.assertEquals(10, largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
