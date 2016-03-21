package com.placeholder.leetcode.math;

/**
 * https://leetcode.com/problems/rectangle-area/
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _223RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = (D - B) * (C - A) + (H - F) * (G - E);
        if (A >= G || B >= H || C <= E || D <= F) {
            return area;
        }

        int top = Math.min(D, H);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int left = Math.max(A, E);

        return area - (top - bottom) * (right - left);
    }
}
