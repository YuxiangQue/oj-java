package com.placeholder.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _54SpiralMatrix {


    private boolean checkLegalMatrix(int[][] matrix) {
        if (matrix == null)
            return false;
        if (matrix.length == 0)
            return false;
        int numOfCols = -1;
        for (int[] row : matrix) {
            if (row == null)
                return false;
            if (row.length == 0)
                return false;
            if (numOfCols == -1) {
                numOfCols = row.length;
            }
            if (numOfCols != row.length) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (!checkLegalMatrix(matrix))
            return new ArrayList<>();

        int numOfRows = matrix.length;
        int numOfCols = matrix[0].length;
        int numOfLayers = Math.min(numOfRows, numOfCols) >> 1;
        List<Integer> spiral = new ArrayList<>();

        int layer = 0;
        for (; layer < numOfLayers; ++layer) {
            // LT to RT
            for (int i = layer; i < numOfCols - 1 - layer; ++i) {
                spiral.add(matrix[layer][i]);
            }
            // RT to RB
            for (int i = layer; i < numOfRows - 1 - layer; ++i) {
                spiral.add(matrix[i][numOfCols - 1 - layer]);
            }
            // RB to LB
            for (int i = numOfCols - 1 - layer; i > layer; --i) {
                spiral.add(matrix[numOfRows - 1 - layer][i]);
            }
            // LB to LT
            for (int i = numOfRows - 1 - layer; i > layer; --i) {
                spiral.add(matrix[i][layer]);
            }
        }

        // 剩下的奇数不能构成圈的
        if (numOfRows <= numOfCols && (numOfRows & 0x01) == 0x01) {
            for (int i = layer; i <= numOfCols - 1 - layer; ++i) {
                spiral.add(matrix[layer][i]);
            }

        } else if (numOfCols < numOfRows && (numOfCols & 0x01) == 0x01) {
            for (int i = layer; i <= numOfRows - 1 - layer; ++i) {
                spiral.add(matrix[i][numOfCols - 1 - layer]);
            }
        }
        return spiral;
    }

    @Test
    public void test() {

        // 1  2  3  4
        // 5  6  7  8
        // 9  10 11 12
        // 13 14 15 16
        spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});


        // 1 2 3
        // 4 5 6
        // 7 8 9
        // 1 2, 3 6, 9 8, 7 4
        // 5
        spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        //  1   2  3
        //  4   5  6
        //  7   8  9
        // 10  11  12
        // 1 2, 3 6 9, 12 11, 10 7 4
        // 5, 8
        spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});
        spiralOrder(new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}});
    }
}
