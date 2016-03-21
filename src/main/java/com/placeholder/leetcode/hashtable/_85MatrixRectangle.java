package com.placeholder.leetcode.hashtable;

/**
 * Created by yuxiangque on 2016/3/18.
 */
public class _85MatrixRectangle {

    // https://leetcode.com/discuss/20240/share-my-dp-solution
    public static int maximalRectangle(char[][] matrix) {
        int numOfRows = matrix.length;
        int numOfCols = matrix[0].length;
        int[] left = new int[numOfCols];
        int[] right = new int[numOfCols];
        int[] height = new int[numOfCols];
        int max = 0;
        for (int row = 0; row < numOfRows; ++row) {
            int currentLeft = 0;
            int currentRight = numOfCols - 1;

            // left
            for (int col = 0; col < numOfCols; ++col) {
                if (matrix[row][col] == '1')
                    left[col] = Math.max(left[col], currentLeft);
                else {
                    left[col] = 0;
                    currentLeft = col + 1;
                }
            }

            // right
            for (int col = numOfCols - 1; col >= 0; --col) {
                if (matrix[row][col] == '1')
                    left[col] = Math.min(left[col], currentRight);
                else {
                    left[col] = 0;
                    currentRight = col - 1;
                }
            }

            // height
            for (int col = 0; col < numOfCols; ++col) {
                if (matrix[row][col] == 1)
                    height[col] += 1;
                else
                    height[col] = 0;
            }

            // area
            for (int col = 0; col < numOfCols; ++col) {
                max = Math.max(max, (right[col] - left[col]) * height[col]);
            }
        }
        return max;
    }

    public static void main(String[] args) {

    }

}
