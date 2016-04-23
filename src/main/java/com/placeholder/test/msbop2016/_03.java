package com.placeholder.test.msbop2016;


public class _03 {

    public static String Puzzle(int r1, int c1, int r2, int c2, String[] strs) {

        char[][] board = new char[strs.length][];
        for (int i = 0; i < strs.length; ++i) {
            board[i] = strs[i].toCharArray();
        }

        if (r1 < 0 || r1 > board.length - 1 || r2 < 0 || r2 > board.length - 1)
            return null;
        if (c1 < 0 || c1 > board[0].length - 1 || c2 < 0 || c2 > board[0].length - 1)
            return null;


        if (board[r1][c1] == '#') {
            return "No path found!";
        }
        if (board[r2][c2] == '#') {
            return "No path found!";
        }

        int[] minStep = new int[1];
        minStep[0] = Integer.MAX_VALUE;
        dfs(r1, c1, r2, c2, board, r1, c1, 0, minStep);
        if (minStep[0] != Integer.MAX_VALUE) {
            return String.valueOf(minStep[0]);
        }
        return "No path found!";
    }

    public static void dfs(int r1, int c1, int r2, int c2, char[][] board, int r, int c, int step,
                           int[] minStep) {
        if (step >= minStep[0]) {
            return;
        }
        if (r2 == r && c2 == c) {
            if (step < minStep[0])
                minStep[0] = step;
        }
        if (r < 0 || r > board.length - 1) {
            return;
        }
        if (c < 0 || c > board[r].length - 1) {
            return;
        }
        if (board[r][c] == '#') {
            return;
        }
        if (board[r][c] == '$') {
            return;
        }
        board[r][c] = '$';
        dfs(r1, c1, r2, c2, board, r - 1, c, step + 1, minStep);
        dfs(r1, c1, r2, c2, board, r + 1, c, step + 1, minStep);
        dfs(r1, c1, r2, c2, board, r, c - 1, step + 1, minStep);
        dfs(r1, c1, r2, c2, board, r, c + 1, step + 1, minStep);
        board[r][c] = '.';
    }
}
