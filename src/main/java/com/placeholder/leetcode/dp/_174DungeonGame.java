package com.placeholder.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/dungeon-game/
 * #dp
 * #bsearch
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _174DungeonGame {


    public boolean dfs(int[][] dungeon, int x, int y, int initHealth, int knightHealth) {
        if (x == dungeon.length - 1 && y == dungeon[0].length - 1) {  // queue
            knightHealth += dungeon[x][y];
            return knightHealth > 0;
        }
        if (x > dungeon.length - 1 || y > dungeon[0].length - 1) {
            return false;
        }
        knightHealth += dungeon[x][y];
        if (knightHealth < 0) {
            return false;
        }
        if (dfs(dungeon, x + 1, y, initHealth, knightHealth) ||
                dfs(dungeon, x, y + 1, initHealth, knightHealth)) {
            return true;
        }
        return false;
    }

    public int calculateMinimumHP1(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0)
            return 0;
        int initHealth = -dungeon[0][0];
        while (true) {
            if (dfs(dungeon, 0, 0, initHealth, initHealth))
                return initHealth;
            initHealth++;
        }
    }

    // need = min{dp[x+1][y],dp[x][y+1]} - dungeon[x][y]
    // dp[x][y] = if need <= 0 then 1 else need
    public int dp(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        for (int x = m - 1; x >= 0; x -= 1) {
            for (int y = n - 1; y >= 0; y -= 1) {
                int need = Math.min(dp[x + 1][y], dp[x][y + 1]) - dungeon[x][y];
                if (need <= 0)
                    need = 1;
                dp[x][y] = need;
            }
        }
        return dp[0][0];
    }


    public int calculateMinimumHP2(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0)
            return 0;
        return dp(dungeon);
    }

    @Test
    public void test() {
        Assert.assertEquals(7, calculateMinimumHP2(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}));
    }
}
