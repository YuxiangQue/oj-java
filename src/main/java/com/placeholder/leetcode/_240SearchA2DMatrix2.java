package com.placeholder.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _240SearchA2DMatrix2 {

    // 从左下角或右上角开始
    public static boolean searchMatrix(int[][] matrix, int target) {
        int numOfRows = matrix.length;
        int numOfCols = matrix[0].length;
        int row = 0;
        int col = numOfCols - 1;

        while (col >= 0 && row < numOfRows) {
            // 往左
            if (target < matrix[row][col])
                --col;
                // 往下
            else if (target > matrix[row][col])
                ++row;
            else
                return true;
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matrix = new int[4][];
        matrix[0] = new int[]{1, 4, 7, 11, 15};
        matrix[1] = new int[]{2, 5, 8, 12, 19};
        matrix[2] = new int[]{10, 13, 14, 17, 24};
        matrix[3] = new int[]{18, 21, 23, 26, 30};
        Assert.assertEquals(true, searchMatrix(matrix, 5));
        Assert.assertEquals(false, searchMatrix(matrix, 20));
    }
}
