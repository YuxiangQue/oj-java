package com.placeholder;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yuxiangque
 * @version 2016/3/26
 */
public class NeteaseGame {

    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    // 网易游戏炮台攻击
    public static String heimerdingerBattery(int R, int x1, int y1, int x2, int y2, int x3, int y3, int x0, int y0) {
        int damage = 0;
        if (distance(x1, y1, x0, y0) <= R) {
            ++damage;
        }
        if (distance(x2, y2, x0, y0) <= R) {
            ++damage;
        }
        if (distance(x3, y3, x0, y0) <= R) {
            ++damage;
        }
        return damage + "x";
    }

    public static void testHeimerdingerBattery() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int R = scanner.nextInt();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            int x3 = scanner.nextInt();
            int y3 = scanner.nextInt();
            int x0 = scanner.nextInt();
            int y0 = scanner.nextInt();
            System.out.println(heimerdingerBattery(R, x1, y1, x2, y2, x3, y3, x0, y0));
        }
    }

    // 扫描透镜
    // 在N*M的草地上,提莫种了K个蘑菇,
    // 蘑菇爆炸的威力极大,兰博不想贸然去闯,
    // 而且蘑菇是隐形的.只 有一种叫做扫描透镜的物品可以扫描出隐形的蘑菇,
    // 于是他回了一趟战争学院,买了2个扫描透镜,
    // 一个 扫描透镜可以扫描出(3*3)方格中所有的蘑菇,然后兰博就可以清理掉一些隐形的蘑菇. 问:兰博最多可以清理多少个蘑菇?
    public static int scanningLens(int[][] mushrooms) {
        return cleanUpMushroom(mushrooms) + cleanUpMushroom(mushrooms);
    }

    private static int cleanUpMushroom(int[][] grass) {
        int[][] mushroomsAround = mushroomsAround(grass);
        // 最大
        int max = -1;
        int maxX = -1;
        int maxY = -1;
        for (int x = 1; x < mushroomsAround.length - 1; ++x) {
            for (int y = 1; y < mushroomsAround[0].length - 1; ++y) {
                if (mushroomsAround[x][y] > max) {
                    max = mushroomsAround[x][y];
                    maxX = x;
                    maxY = y;
                }
            }
        }
        grass[maxX - 1][maxY - 1] -= 1;
        grass[maxX - 1][maxY] -= 1;
        grass[maxX - 1][maxY + 1] -= 1;
        grass[maxX][maxY - 1] -= 1;
        grass[maxX][maxY] -= 1;
        grass[maxX][maxY + 1] -= 1;
        grass[maxX + 1][maxY - 1] -= 1;
        grass[maxX + 1][maxY] -= 1;
        grass[maxX + 1][maxY + 1] -= 1;
        return max;
    }

    // 积分草地
    private static int[][] mushroomsAround(int[][] mushrooms) {
        int[][] mushroomsAround = new int[mushrooms.length][mushrooms[0].length];
        for (int x = 1; x < mushrooms.length - 1; ++x) {
            for (int y = 1; y < mushrooms[0].length - 1; ++y) {
                mushroomsAround[x][y] = mushrooms[x - 1][y - 1] +
                        mushrooms[x - 1][y] +
                        mushrooms[x - 1][y + 1] +
                        mushrooms[x][y - 1] +
                        mushrooms[x][y] +
                        mushrooms[x][y + 1] +
                        mushrooms[x + 1][y - 1] +
                        mushrooms[x + 1][y] +
                        mushrooms[x + 1][y + 1];
            }
        }
        return mushroomsAround;
    }

    public static void testScanningLens() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();
            int[][] grass = new int[n][m];
            for (int i = 0; i < k; ++i) {
                grass[scanner.nextInt() - 1][scanner.nextInt() - 1] += 1;
            }
            System.out.println(Arrays.deepToString(grass));
            System.out.println(scanningLens(grass));
        }
    }


    public static void main(String[] args) {
        testScanningLens();
    }

    @Test
    public void test() {
        int[][] grass = new int[][]{{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};
        Assert.assertEquals(60, scanningLens(grass));
    }

}
