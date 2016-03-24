package com.placeholder.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _59SpiralMatrix2 {


    public int[][] generateMatrix(int n) {
        if (n <= 0)
            return new int[0][0];
        int[][] matrix = new int[n][n];

        int count = 1;
        int layer = 0;
        int numOfLayers = n >> 1;
        for (; layer < numOfLayers; ++layer) {
            // LT to RT
            for (int index = layer; index < n - 1 - layer; ++index) {
                matrix[layer][index] = count++;
            }
            // RT to RB
            for (int i = layer; i < n - 1 - layer; ++i) {
                matrix[i][n - 1 - layer] = count++;
            }
            // RB to LB
            for (int i = n - 1 - layer; i > layer; --i) {
                matrix[n - 1 - layer][i] = count++;
            }
            // LB to LT
            for (int i = n - 1 - layer; i > layer; --i) {
                matrix[i][layer] = count++;
            }
        }

        // 剩下的奇数不能构成圈的
        if ((n & 0x01) == 0x01) {
            for (int i = layer; i <= n - 1 - layer; ++i) {
                matrix[layer][i] = count++;
            }

        }
        return matrix;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}, generateMatrix(3));
        Assert.assertArrayEquals(new int[][]{{1, 2}, {4, 3}}, generateMatrix(2));
        Assert.assertArrayEquals(new int[][]{{1}}, generateMatrix(1));
    }
}