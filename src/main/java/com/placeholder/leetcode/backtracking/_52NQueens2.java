package com.placeholder.leetcode.backtracking;

/**
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _52NQueens2 {

    // 迭代求解
    public static int totalNQueensIter(int n) {
        return 0;
    }

    static class MethodNQueens {
        public static int totalNQueens(int n) {
            int[] total = new int[1];
            int[] colForRow = new int[n];
            helper(n, colForRow, 0, total);
            return total[0];
        }

        /**
         * @param n            num of queens
         * @param row          row index
         * @param columnForRow the array which holds the groups
         */
        private static void helper(int n, int[] columnForRow, int row, int[] total) {
            // last row
            if (row == n) {
                total[0] += 1;
                return;
            }
            for (int i = 0; i < n; ++i) {
                columnForRow[row] = i;
                if (isValid(columnForRow, row)) {
                    helper(n, columnForRow, row + 1, total);
                }
                columnForRow[row] = 0;
            }
        }

        private static boolean isValid(int[] colForRow, int row) {
            for (int i = 0; i < row; i++) {
                if (colForRow[row] == colForRow[i] || Math.abs(colForRow[row] - colForRow[i]) == row - i) {
                    return false;
                }
            }
            return true;
        }
    }
}
