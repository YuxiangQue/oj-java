package com.placeholder.leetcode;

import java.util.TreeSet;

public class _363MaxSumOfRectangleNoLargerThanK {


    public static int areaRange(int[][] areas, int x1, int y1, int x2, int y2) {
        return areas[x2 + 1][y2 + 1] - areas[x1][y2 + 1] - areas[x2 + 1][y1] + areas[x1][y1];
    }


    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length, m = matrix[0].length;
        int[][] areas = new int[n + 1][m + 1];

        // 列
        for (int y = 1; y <= m; ++y) {
            for (int x = 1; x <= n; ++x) {
                areas[x][y] = matrix[x - 1][y - 1] + areas[x][y - 1];
            }
        }

        // 行
        for (int x = 1; x <= n; ++x) {
            for (int y = 1; y <= m; ++y) {
                areas[x][y] = areas[x][y] + areas[x - 1][y];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int x1 = 0; x1 < n; x1++) {
            for (int x2 = x1; x2 < n; x2++) {
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int y2 = 0; y2 < m; y2++) {
                    int area = areaRange(areas, x1, 0, x2, y2);
                    Integer ceiling = set.ceiling(area - k);
                    if (ceiling != null) {
                        max = Math.max(max, area - ceiling);
                    }
                    set.add(area);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 0, 1}, {0, -2, 3}};
        int k = 2;
        System.out.println(maxSumSubmatrix(matrix, k));
    }
}
