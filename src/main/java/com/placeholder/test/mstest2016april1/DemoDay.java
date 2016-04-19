package com.placeholder.test.mstest2016april1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/mstest2016april1/problem/3
 *
 * @author yuxiangque
 * @version 2016/4/6
 */
public class DemoDay {

    public static int maze(String[] lines) {
        if (lines == null || lines.length == 0)
            return -1;
        int n = lines.length;
        int m = lines[0].length();

        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;

        char[][] maze = new char[n][m];
        for (int i = 0; i < lines.length; i++) {
            String[] col = lines[i].split("");
            for (int j = 0; j < col.length; j++) {
                maze[i][j] = col[j].charAt(0);
            }
        }
        dfs(maze, '0', 0, 0, 0, min);

        if (min[0] == Integer.MAX_VALUE) {
            return -1;
        }
        return min[0];
    }

    // List<Integer> pathX = new ArrayList<>();
    // List<Integer> pathY = new ArrayList<>();
    // List<Integer> pathR = new ArrayList<>();

    private static void dfs(char[][] maze, char dir, int x, int y, int r, int[] minR) {
        if (r >= minR[0]) {
            return;
        }
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            // System.out.println(pathX);
            // System.out.println(pathY);
            // System.out.println(pathR);
            // System.out.println();
            if (r < minR[0])
                minR[0] = r;
            return;
        }
        if (wall(maze, x, y)) {
            return;
        }
        // pathX.add(x);
        // pathY.add(y);
        // pathR.add(r);
        // 超右走
        if (dir == '0') {
            if (wall(maze, x, y + 1)) {
                dfs(maze, '0', x, y + 1, r, minR);

                // 朝下走
                if (wall(maze, x + 1, y)) {
                    dfs(maze, '1', x + 1, y, r, minR);
                } else if (rock(maze, x + 1, y)) {
                    dfs(maze, '1', x + 1, y, r + 1, minR);
                } else {
                    dfs(maze, '1', x + 1, y, r, minR);
                }

            } else if (rock(maze, x, y + 1)) {
                dfs(maze, '0', x, y + 1, r + 1, minR);

                // 朝下走
                if (wall(maze, x + 1, y)) {
                    dfs(maze, '1', x + 1, y, r, minR);
                } else if (rock(maze, x + 1, y)) {
                    dfs(maze, '1', x + 1, y, r + 1, minR);
                } else {
                    dfs(maze, '1', x + 1, y, r, minR);
                }
            } else {
                dfs(maze, '0', x, y + 1, r, minR);

                // 朝下走
                if (wall(maze, x + 1, y)) {
                    dfs(maze, '1', x + 1, y, r + 1, minR);
                } else if (rock(maze, x + 1, y)) {
                    dfs(maze, '1', x + 1, y, r + 2, minR);
                } else {
                    dfs(maze, '1', x + 1, y, r + 1, minR);
                }
            }


        } else if (dir == '1') {

            // 朝下走
            if (wall(maze, x + 1, y)) {
                dfs(maze, '1', x + 1, y, r, minR);

                // 超右走
                if (wall(maze, x, y + 1)) {
                    dfs(maze, '0', x, y + 1, r, minR);
                } else if (rock(maze, x, y + 1)) {
                    dfs(maze, '0', x, y + 1, r + 1, minR);
                } else {
                    dfs(maze, '0', x, y + 1, r, minR);
                }
            } else if (rock(maze, x + 1, y)) {
                dfs(maze, '1', x + 1, y, r + 1, minR);

                // 超右走
                if (wall(maze, x, y + 1)) {
                    dfs(maze, '0', x, y + 1, r, minR);
                } else if (rock(maze, x, y + 1)) {
                    dfs(maze, '0', x, y + 1, r + 1, minR);
                } else {
                    dfs(maze, '0', x, y + 1, r, minR);
                }
            } else {
                dfs(maze, '1', x + 1, y, r, minR);

                // 超右走
                if (wall(maze, x, y + 1)) {
                    dfs(maze, '0', x, y + 1, r + 1, minR);
                } else if (rock(maze, x, y + 1)) {
                    dfs(maze, '0', x, y + 1, r + 2, minR);
                } else {
                    dfs(maze, '0', x, y + 1, r + 1, minR);
                }
            }
        }
        // pathX.remove(pathX.size() - 1);
        // pathY.remove(pathY.size() - 1);
        // pathR.remove(pathR.size() - 1);
    }

    private static boolean rock(char[][] maze, int x, int y) {
        return Objects.equals(maze[x][y], 'b') || Objects.equals(maze[x][y], 'B');
    }

    private static boolean wall(char[][] maze, int x, int y) {
        if (x < 0 || x >= maze.length) {
            return true;
        }
        if (y < 0 || y >= maze[0].length) {
            return true;
        }
        return false;
    }

    /**
     * 4 8
     * ....bb..
     * ........
     * .....b..
     * ...bb...
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String[][] maze = new String[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split("");
            for (int j = 0; j < line.length; j++) {
                maze[i][j] = line[j];
            }
        }

    }

    @Test
    public void test() {
        Assert.assertEquals(1, maze(new String[]{
                "....bb..",
                "........",
                ".....b..",
                "...bb..."}));
        Assert.assertEquals(1, maze(new String[]{
                "....bb..",
                ".....b..",
                ".....b..",
                "...bb..."}));

        Assert.assertEquals(2, maze(new String[]{
                "....bb..",
                ".....b..",
                ".....bbb",
                "...bbb.."}));
        Assert.assertEquals(1, maze(new String[]{
                "....bb..",
                ".....b..",
                ".....bbb",
                "...bb..."}));
    }
}
