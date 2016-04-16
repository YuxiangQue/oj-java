package com.placeholder.leetcode;

import org.junit.Test;

/**
 * https://leetcode.com/problems/number-of-islands/
 * <p>
 * #Depth-first Search
 * #Breadth-first Search
 * #Union Find
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _200NumberOfIslands {

    private boolean floodFill(char[][] grid, int x, int y) {
        if (x < 0 || x > grid.length - 1) {
            return false;
        }
        if (y < 0 || y > grid[0].length - 1) {
            return false;
        }
        if (grid[x][y] == '1') {  // islands
            // filled
            grid[x][y] = 'x';
            floodFill(grid, x - 1, y);
            floodFill(grid, x + 1, y);
            floodFill(grid, x, y - 1);
            floodFill(grid, x, y + 1);
            return true;
        }
        return false;
    }

    public int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                num += floodFill(grid, i, j) ? 1 : 0;
            }
        }
        return num;
    }

    @Test
    public void test() {
        int num = numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}});
        System.out.println(num);

        num = numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}});
        System.out.println(num);
    }
}
