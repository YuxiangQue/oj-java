package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _51NQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] colForRow = new int[n];
        dfs(n, colForRow, 0, result);
        return result;
    }

    private static boolean isValid(int[] colForRow, int row) {
        for (int i = 0; i < row; i++) {
            if (colForRow[row] == colForRow[i] || Math.abs(colForRow[row] - colForRow[i]) == row - i) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param n            num of queens
     * @param row          row index
     * @param columnForRow the array which holds the groups
     * @param result
     */
    private static void dfs(int n, int[] columnForRow, int row, List<List<String>> result) {
        // last row
        if (row == n) {
            // generate groups
            List<String> item = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                StringBuilder strRow = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (columnForRow[i] == j)
                        strRow.append('Q');
                    else
                        strRow.append('.');
                }
                item.add(i, strRow.toString());
            }
            result.add(item);
            return;
        }
        for (int i = 0; i < n; ++i) {
            columnForRow[row] = i;
            if (isValid(columnForRow, row)) {
                dfs(n, columnForRow, row + 1, result);
            }
            columnForRow[row] = 0;
        }
    }
}
