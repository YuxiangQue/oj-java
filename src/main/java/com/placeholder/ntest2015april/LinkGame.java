package com.placeholder.ntest2015april;

import org.junit.Assert;
import org.junit.Test;

/**
 * http://hihocoder.com/contest/ntest2015april/problem/3
 *
 * @author yuxiangque
 * @version 2016/4/9
 */
public class LinkGame {

    @Test
    public void test() {
        LinkGameSolver solver = new LinkGameSolver();
        Assert.assertEquals(3, solver.link(new int[][]{
                        {1, 0, 1, 0, 2},
                        {0, 0, 1, 3, 1},
                        {3, 3, 1, 5, 9},
                        {6, 1, 4, 8, 7}},
                0, 2, 3));
        Assert.assertEquals(2, solver.link(new int[][]{
                        {1, 0, 1, 0, 2},
                        {0, 0, 1, 3, 1},
                        {3, 3, 1, 5, 9},
                        {6, 1, 4, 8, 7}},
                0, 2, 1));
        Assert.assertEquals(0, solver.link(new int[][]{
                        {1, 10},
                        {2, 3}},
                0, 0, 10));
    }

    public static class LinkGameSolver {
        int[][] board;
        int start;
        int startX;
        int startY;
        int k;
        int count;

        public int link(int[][] board, int x, int y, int k) {
            if (board == null || board.length == 0)
                return 0;
            if (x < 0 || x >= board.length)
                return 0;
            if (y < 0 || y >= board[0].length)
                return 0;
            if (board[x][y] == 0)
                return 0;
            this.board = board;
            this.k = k;
            startX = x;
            startY = y;
            start = board[startX][startY];
            count = 0;
            board[startX][startY] = -1;
            dfs(x, y - 1, Direction.Horizontal, 0);
            dfs(x, y + 1, Direction.Horizontal, 0);
            dfs(x - 1, y, Direction.Vertical, 0);
            dfs(x + 1, y, Direction.Vertical, 0);
            board[startX][startY] = 0;
            return count;
        }

        private void dfs(int x, int y, Direction dir, int turn) {
            if (turn >= this.k) {
                return;
            }
            if (x < 0 || x >= board.length) {
                return;
            }
            if (y < 0 || y >= board[0].length) {
                return;
            }
            if (board[x][y] == -1) { // visited
                return;
            }
            if (board[x][y] == start) {
                ++count;
                return;
            }
            if (board[x][y] == 0) {
                board[x][y] = -1;  // 访问过
                if (dir == Direction.Horizontal) {  // 水平
                    dfs(x, y - 1, Direction.Horizontal, turn);
                    dfs(x, y + 1, Direction.Horizontal, turn);
                    dfs(x - 1, y, Direction.Vertical, turn + 1);
                    dfs(x + 1, y, Direction.Vertical, turn + 1);
                } else if (dir == Direction.Vertical) {  // 垂直
                    dfs(x - 1, y, Direction.Vertical, turn);
                    dfs(x + 1, y, Direction.Vertical, turn);
                    dfs(x, y - 1, Direction.Horizontal, turn + 1);
                    dfs(x, y + 1, Direction.Horizontal, turn + 1);
                }
                board[x][y] = 0;
            }
        }

        private enum Direction {
            Horizontal,
            Vertical
        }
    }
}
