package com.placeholder.leetcode.backtracking;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * http://blog.csdn.net/linhuanmars/article/details/20748761
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _37SudokuSolver {

    public static void main(String[] args) {
        String[] board = new String[]{"..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.", "........6", "...2759.."};
        char[][] board1 = new char[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                board1[i][j] = board[i].charAt(j);
            }
        }
        System.out.println(Arrays.deepToString(board1));
        new _37SudokuSolver().solveSudoku(board1);
        System.out.println(Arrays.deepToString(board1));
    }

    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return;
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int row, int col) {
        if (col >= 9)
            return dfs(board, row + 1, 0);
        if (row == 9)
            return true;
        if (board[row][col] == '.') {
            for (int i = 1; i <= 9; ++i) {
                board[row][col] = (char) (i + '0');
                if (isValid(board, row, col)) {
                    if (dfs(board, row, col + 1)) {
                        return true;
                    }
                }
                board[row][col] = '.';
            }
        } else {
            return dfs(board, row, col + 1);
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < 9; ++i) {
            if (i != row && board[i][col] == board[row][col])
                return false;
        }
        for (int j = 0; j < 9; ++j) {
            if (j != col && board[row][j] == board[row][col])
                return false;
        }
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; ++i) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; ++j) {
                if ((i != row || j != col) && board[row][col] == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
