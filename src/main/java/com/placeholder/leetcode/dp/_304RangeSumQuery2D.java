package com.placeholder.leetcode.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author yuxiangque
 * @version 2016/4/4
 */
public class _304RangeSumQuery2D {

    @Test
    public void test() {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}});
        assertEquals(3, numMatrix.sumRegion(0, 0, 0, 0));
        assertEquals(6, numMatrix.sumRegion(1, 1, 1, 1));
        assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        assertEquals(11, numMatrix.sumRegion(1, 1, 2, 2));
        assertEquals(12, numMatrix.sumRegion(1, 2, 2, 4));

        numMatrix = new NumMatrix(new int[][]{});
        assertEquals(0, numMatrix.sumRegion(0, 0, 0, 0));

        numMatrix = new NumMatrix(new int[][]{{}});
        assertEquals(0, numMatrix.sumRegion(0, 0, 0, 0));
    }

    static class NumMatrix {

        int[][] accMatrix; // 积分图像
        int numOfRows = 0;
        int numOfCols = 0;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                accMatrix = new int[1][1];
                return;
            }
            numOfRows = matrix.length;
            numOfCols = matrix[0].length;
            accMatrix = new int[numOfRows][numOfCols];
            for (int i = 0; i < numOfRows; i++) {
                for (int j = 0; j < numOfCols; j++) {
                    if (i == 0 && j == 0) {
                        accMatrix[i][j] = matrix[i][j];
                    } else if (i == 0) { // 第一行
                        accMatrix[i][j] = accMatrix[i][j - 1] + matrix[i][j];
                    } else if (j == 0) { // 第一列
                        accMatrix[i][j] = accMatrix[i - 1][j] + matrix[i][j];
                    } else {
                        accMatrix[i][j] = accMatrix[i - 1][j] + accMatrix[i][j - 1] - accMatrix[i - 1][j - 1] + matrix[i][j];
                    }
                }
            }
            return;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 == 0 && col1 == 0) {
                return accMatrix[row2][col2];
            } else if (row1 == 0) { // 第一行开始
                return accMatrix[row2][col2] - accMatrix[row2][col1 - 1];
            } else if (col1 == 0) { // 第一列开始
                return accMatrix[row2][col2] - accMatrix[row1 - 1][col2];
            } else {
                return accMatrix[row2][col2] - (accMatrix[row1 - 1][col2] + accMatrix[row2][col1 - 1] - accMatrix[row1 - 1][col1 - 1]);
            }
        }
    }
}
