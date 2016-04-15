package com.placeholder.common.segment_tree;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/4/14
 */
public class SumSegmentTree2D {

    static final int MaxSize = 1024;
    int[] sum;     // 保存所有的子节点的sum
    int[][] arr;    // 数据
    int numOfRows;
    int numOfCols;

    public SumSegmentTree2D(int[][] arr) {
        numOfRows = arr.length;
        numOfCols = arr[0].length;
        int temp = Math.max(numOfRows, numOfCols);
        this.arr = arr;
        sum = new int[4 * temp * temp];
        build(0, 0, 0, numOfRows - 1, numOfCols - 1);
    }

    public void update(int row1, int col1, int value) {
        if (row1 < 0 || row1 > numOfRows) {
            return;
        }
        if (col1 < 0 || col1 > numOfCols) {
            return;
        }
        update(0, 0, 0, numOfRows - 1, numOfCols - 1, row1, col1, value);
    }

    public int sum(int row1, int col1, int row2, int col2) {
        if (row1 < 0 || row1 > numOfRows) {
            return 0;
        }
        if (col1 < 0 || col1 > numOfCols) {
            return 0;
        }
        if (row2 < 0 || row2 > numOfRows) {
            return 0;
        }
        if (col2 < 0 || col2 > numOfCols) {
            return 0;
        }
        if (row1 > row2 || col1 > col2) {
            return 0;
        }
        return sum(0, 0, 0, numOfRows - 1, numOfCols - 1, row1, col1, row2, col2);
    }

    private int build(int node, int minRow1, int minCol1, int minRow2, int minCol2) {
        if (minRow1 > minRow2 || minCol1 > minCol2)
            return 0;
        if (minRow1 == minRow2 && minCol1 == minCol2) {
            sum[node] = arr[minRow1][minCol1];
            return sum[node];
        }
        int sum = 0;
        sum += build(4 * node + 1, minRow1, minCol1, (minRow1 + minRow2) / 2, (minCol1 + minCol2) / 2);
        sum += build(4 * node + 2, (minRow1 + minRow2) / 2 + 1, minCol1, minRow2, (minCol1 + minCol2) / 2);
        sum += build(4 * node + 3, minRow1, (minCol1 + minCol2) / 2 + 1, (minRow1 + minRow2) / 2, minCol2);
        sum += build(4 * node + 4, (minRow1 + minRow2) / 2 + 1, (minCol1 + minCol2) / 2 + 1, minRow2, minCol2);
        this.sum[node] = sum;
        return sum;
    }

    private int sum(int node, int minRow1, int minCol1, int minRow2, int minCol2, int row1, int col1, int row2, int col2) {
        if (minRow1 > minRow2 || minCol1 > minCol2)
            return 0;
        if (row1 > minRow2 || col1 > minCol2)
            return 0;
        if (row2 < minRow1 || col2 < minCol1)
            return 0;
        if (row1 <= minRow1 && col1 <= minCol1 && minRow2 <= row2 && minCol2 <= col2)  // if it is within range, return the node
            return sum[node];
        int sum = 0;
        sum += sum(4 * node + 1, minRow1, minCol1, (minRow1 + minRow2) / 2, (minCol1 + minCol2) / 2, row1, col1, row2, col2);
        sum += sum(4 * node + 2, (minRow1 + minRow2) / 2 + 1, minCol1, minRow2, (minCol1 + minCol2) / 2, row1, col1, row2, col2);
        sum += sum(4 * node + 3, minRow1, (minCol1 + minCol2) / 2 + 1, (minRow1 + minRow2) / 2, minCol2, row1, col1, row2, col2);
        sum += sum(4 * node + 4, (minRow1 + minRow2) / 2 + 1, (minCol1 + minCol2) / 2 + 1, minRow2, minCol2, row1, col1, row2, col2);
        return sum;
    }

    private int update(int node, int minRow1, int minCol1, int minRow2, int minCol2, int row, int col, int newValue) {
        if (minRow1 > minRow2 || minCol1 > minCol2)
            return 0;
        if (row < minRow1 || col < minCol1 || row > minRow2 || col > minCol2)
            return sum[node];
        if (row == minRow1 && col == minCol1 && row == minRow2 && col == minCol2) {
            arr[row][col] = newValue;
            sum[node] = newValue;
            return sum[node];
        }
        int sum = 0;
        sum += update(4 * node + 1, minRow1, minCol1, (minRow1 + minRow2) / 2, (minCol1 + minCol2) / 2, row, col, newValue);
        sum += update(4 * node + 2, (minRow1 + minRow2) / 2 + 1, minCol1, minRow2, (minCol1 + minCol2) / 2, row, col, newValue);
        sum += update(4 * node + 3, minRow1, (minCol1 + minCol2) / 2 + 1, (minRow1 + minRow2) / 2, minCol2, row, col, newValue);
        sum += update(4 * node + 4, (minRow1 + minRow2) / 2 + 1, (minCol1 + minCol2) / 2 + 1, minRow2, minCol2, row, col, newValue);
        this.sum[node] = sum;
        return sum;
    }

    public static class CoverSumSumSegmentTree2DTest {
        @Test
        public void test() {
            SumSegmentTree2D sumSegmentTree2D1 = new SumSegmentTree2D(new int[][]{{1}});
            // Assert.assertEquals(1, sumSegmentTree2D1.sum(0, 0, 0, 0));

            SumSegmentTree2D sumSegmentTree2D2 = new SumSegmentTree2D(new int[][]{{1, 2}, {3, 4}});
            //  Assert.assertEquals(1, sumSegmentTree2D2.sum(0, 0, 0, 0));
            //   Assert.assertEquals(4, sumSegmentTree2D2.sum(0, 0, 1, 0));
            //  Assert.assertEquals(3, sumSegmentTree2D2.sum(0, 0, 0, 1));
            Assert.assertEquals(10, sumSegmentTree2D2.sum(0, 0, 1, 1));
            sumSegmentTree2D2.update(0, 0, 3);
            Assert.assertEquals(3, sumSegmentTree2D2.sum(0, 0, 0, 0));
            Assert.assertEquals(6, sumSegmentTree2D2.sum(0, 0, 1, 0));
            Assert.assertEquals(5, sumSegmentTree2D2.sum(0, 0, 0, 1));
            Assert.assertEquals(12, sumSegmentTree2D2.sum(0, 0, 1, 1));

            SumSegmentTree2D sumSegmentTree2D3 = new SumSegmentTree2D(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
            Assert.assertEquals(1, sumSegmentTree2D3.sum(0, 0, 0, 0));
            Assert.assertEquals(5, sumSegmentTree2D3.sum(0, 0, 1, 0));
            Assert.assertEquals(3, sumSegmentTree2D3.sum(0, 0, 0, 1));
            Assert.assertEquals(12, sumSegmentTree2D3.sum(0, 0, 1, 1));
        }
    }
}
