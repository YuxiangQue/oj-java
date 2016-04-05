package com.placeholder.leetcode.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * {@link _84LargestRectangleInHistogram}
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * @author yuxiangque
 * @version 2016/4/4
 */
public class _85MaximalRectangle {

    private static boolean validMatrix(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return false;
        if (matrix[0].length == 0)
            return false;
        return true;
    }

    // 1 0 1 0 0
    // 1 0 1 1 1
    // 1 1 1 1 1
    // 1 0 0 1 0
    // 1 1 1 1 1
    @Test
    public void test() {
        assertEquals(1, On2.maximalRectangle(new char[][]{
                {'1'}}));
        assertEquals(0, On2.maximalRectangle(new char[][]{
                {'0'}}));
        assertEquals(3, On2.maximalRectangle(new char[][]{
                {'1', '1', '1'},
                {'1', '0', '1'},
                {'1', '1', '1'}}));
        assertEquals(5, On2.maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '0', '0', '1', '0'},
                {'1', '1', '1', '1', '1'}}));
    }

    public static class On2 {

        private static int[] heights(char[][] matrix, int row) {
            int numOfCols = matrix[0].length;
            int[] heights = new int[numOfCols];
            for (int j = 0; j < numOfCols; j++) {
                int i = row;
                for (; i >= 0 && matrix[i][j] != '0'; --i) {  // 0 to row
                }
                heights[j] = row - i;
            }
            return heights;
        }

        public static int maximalRectangle(char[][] matrix) {
            if (!validMatrix(matrix))
                return 0;
            int max = 0;
            for (int row = 0; row < matrix.length; row++) {
                int[] heights = heights(matrix, row);
                int temp = _84LargestRectangleInHistogram.largestRectangleArea(heights);
                max = temp > max ? temp : max;
            }
            return max;
        }
    }

    // TODO
    public static class On3 {
        public static int maximalRectangle(char[][] matrix) {
            if (!validMatrix(matrix)) {
                return 0;
            }
            List<Rect> rects = new ArrayList<>();
            RectCell[][] cellMatrix = rightBottom(matrix);
            for (int height = 1; height <= matrix.length; ++height) {
                for (int width = 1; width <= matrix[0].length; ++width) {
                    Rect rect = findRect(cellMatrix, width, height);
                    if (rect == null) {
                        break;
                    }
                    rects.add(rect);
                }
            }
            Rect max = maxRect(rects);
            if (max == null)
                return 0;
            return max.area();
        }

        private static Rect findRect(RectCell[][] cellMatrix, int width, int height) {
            for (int row = 0; row <= cellMatrix.length - height; row++) {
                for (int col = 0; col <= cellMatrix[0].length - width; col++) {
                    if (isRect(cellMatrix, row, col, width, height)) {
                        return new Rect(row, col, width, height);
                    }
                }
            }
            return null;
        }

        private static boolean isRect(RectCell[][] cellMatrix, int row, int col, int width, int height) {
            RectCell topLeft = cellMatrix[row][col];
            RectCell topRight = cellMatrix[row][col + width - 1];
            RectCell bottomLeft = cellMatrix[row + height - 1][col];
            if (topLeft.right < width) {
                return false;
            }
            if (topLeft.bottom < height) {
                return false;
            }
            if (topRight.bottom < height) {
                return false;
            }
            if (bottomLeft.right < width) {
                return false;
            }
            return true;
        }

        private static RectCell[][] rightBottom(char[][] matrix) {
            RectCell[][] cellMatrix = new RectCell[matrix.length][matrix[0].length];
            for (int i = matrix.length - 1; i >= 0; --i) {
                for (int j = matrix[0].length - 1; j >= 0; --j) {
                    int right = 0;
                    int bottom = 0;
                    if (matrix[i][j] == '1') {
                        ++right;
                        ++bottom;
                        if (j < matrix[0].length - 1) {
                            right += cellMatrix[i][j + 1].right;
                        }
                        if (i < matrix.length - 1) {
                            bottom += cellMatrix[i + 1][j].bottom;
                        }
                    }
                    cellMatrix[i][j] = new RectCell(right, bottom);
                }
            }
            return cellMatrix;
        }

        private static Rect maxRect(List<Rect> rects) {
            if (rects.size() == 0)
                return null;
            Rect max = rects.get(0);
            for (int i = 1; i < rects.size(); i++) {
                Rect rect = rects.get(i);
                if (rect.area() > max.area())
                    max = rect;
            }
            return max;
        }

        public static class RectCell {
            int right;
            int bottom;

            RectCell(int right, int bottom) {
                this.right = right;
                this.bottom = bottom;
            }
        }

        public static class Rect {
            int left;
            int top;
            int width;
            int height;

            public Rect(int left, int top, int width, int height) {
                this.left = left;
                this.top = top;
                this.width = width;
                this.height = height;
            }

            public int area() {
                return width * height;
            }
        }
    }


}
