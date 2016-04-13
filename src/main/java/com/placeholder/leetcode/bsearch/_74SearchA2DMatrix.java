package com.placeholder.leetcode.bsearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _74SearchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] array = new int[row * col];
        for (int i = 0; i < row; ++i) {
            System.arraycopy(matrix[i], 0, array, col * i, col);
        }
        int tmp = Arrays.binarySearch(array, target);
        return tmp >= 0;
    }

    @Test
    public void test() {
        int[][] matrix = new int[3][];
        matrix[0] = new int[]{1, 3, 5, 7};
        matrix[1] = new int[]{10, 11, 16, 20};
        matrix[2] = new int[]{23, 30, 34, 50};
        Assert.assertEquals(true, searchMatrix(matrix, 3));
        Assert.assertEquals(false, searchMatrix(new int[][]{{1}}, 2));
    }
}
