package com.placeholder.leetcode;

public class _529Minesweeper {

    public static boolean in(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public static void dfs(char[][] board, int x, int y) {
        if (!in(board, x, y)) {
            return;
        }

        if (board[x][y] == 'E') {
            int adjacentMimes = 0;
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dy = -1; dy <= 1; ++dy) {
                    if (dx == 0 && dy == 0) {
                        continue;
                    }
                    if (!in(board, x + dx, y + dy)) {
                        continue;
                    }
                    if (board[x + dx][y + dy] == 'M') {
                        adjacentMimes += 1;
                    }
                }
            }
            if (adjacentMimes != 0) {
                board[x][y] = (char) (adjacentMimes + '0');
            } else {
                board[x][y] = 'B';
                for (int dx = -1; dx <= 1; ++dx) {
                    for (int dy = -1; dy <= 1; ++dy) {
                        if (dx == 0 && dy == 0) {
                            continue;
                        }
                        dfs(board, x + dx, y + dy);
                    }
                }
            }
        }
    }


    public static char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            dfs(board, x, y);
        }
        return board;
    }

    public static void main(String[] args) {
        String[] b = new String[]{"EEEEEEEE", "EEEEEEEM", "EEMEEEEE", "MEEEEEEE", "EEEEEEEE", "EEEEEEEE", "EEEEEEEE", "EEMMEEEE"};
        int n = b.length, m = b[0].length();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; ++i) {
            board[i] = b[i].toCharArray();
        }
        updateBoard(board, new int[]{0, 0});
        return;
    }
}
