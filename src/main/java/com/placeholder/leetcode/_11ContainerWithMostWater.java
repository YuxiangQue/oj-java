package com.placeholder.leetcode;

/**
 * @author yuxiangque
 * @version 2016/4/13
 */
public class _11ContainerWithMostWater {
    int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int area = Math.min(height[left], height[right]) * (right - left);
        while (left < right) {
            int tmpArea = Math.min(height[left], height[right]) * (right - left);
            area = tmpArea > area ? tmpArea : area;
            if (height[left] < height[right])
                ++left;
            else
                --right;
        }
        return area;
    }
}
