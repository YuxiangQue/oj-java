package com.placeholder.ntest2015april;

import org.junit.Assert;
import org.junit.Test;

/**
 * http://hihocoder.com/contest/ntest2015april/problem/1
 * 推箱子
 *
 * @author yuxiangque
 * @version 2016/4/9
 */
public class Sokoban {

    public static boolean sokoban(int[][] sokoban, String directions) {
        if (sokoban == null || sokoban.length == 0) {
            return false;
        }

        int numOfRows = sokoban.length;
        int numOfCols = sokoban[0].length;
        int playerX = 0, playerY = 0;
        int boxX = 0, boxY = 0;
        int targetX = 0, targetY = 0;

        for (int row = 0; row < sokoban.length; row++) {
            for (int col = 0; col < sokoban[0].length; col++) {
                if (sokoban[row][col] == 1) {
                    playerX = row;
                    playerY = col;
                }
                if (sokoban[row][col] == 2) {
                    targetX = row;
                    targetY = col;
                }
                if (sokoban[row][col] == 3) {
                    boxX = row;
                    boxY = col;
                }
            }
        }
        for (int i = 0; i < directions.length(); i++) {
            char ch = directions.charAt(i);
            if (ch == 'l') {
                if (boxY + 1 == playerY && boxX == playerX) {  // 接触
                    if (boxY != 0) {
                        boxY -= 1;
                        playerY -= 1;
                    }
                } else {
                    if (playerY != 0) {
                        playerY -= 1;
                    }
                }
            } else if (ch == 'r') {
                if (playerY + 1 == boxY && boxX == playerX) {  // 接触
                    if (boxY != numOfCols - 1) {
                        boxY += 1;
                        playerY += 1;
                    }
                } else {
                    if (playerY != numOfCols - 1) {
                        playerY += 1;
                    }
                }
            } else if (ch == 'u') {
                if (playerX - 1 == boxX && boxY == playerY) {  // 接触
                    if (boxX != 0) {
                        boxX -= 1;
                        playerX -= 1;
                    }
                } else {
                    if (playerX != 0) {
                        playerX -= 1;
                    }
                }
            } else if (ch == 'd') {
                if (playerX + 1 == boxX && boxY == playerY) {  // 接触
                    if (boxX != numOfRows - 1) {
                        boxX += 1;
                        playerX += 1;
                    }
                } else {
                    if (playerX != numOfRows - 1) {
                        playerX += 1;
                    }
                }
            }

            // 目标点
            if (boxX == targetX && boxY == targetY) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertEquals(true, sokoban(new int[][]{{0, 0, 0, 0, 0}, {1, 3, 0, 0, 0}, {0, 0, 2, 0, 0}, {0, 0, 0, 0, 0}}, "rurd"));
        Assert.assertEquals(true, sokoban(new int[][]{{0, 0, 0, 0, 0}, {1, 3, 0, 0, 0}, {0, 0, 2, 0, 0}, {0, 0, 0, 0, 0}}, "urdldr"));
        Assert.assertEquals(false, sokoban(new int[][]{{0, 0, 0, 0, 0}, {1, 3, 0, 0, 0}, {0, 0, 2, 0, 0}, {0, 0, 0, 0, 0}}, "rrrurd"));
    }
}
