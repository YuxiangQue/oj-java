package com.placeholder.hihoCoder.weekly;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Image Encryption
 * http://hihocoder.com/discuss/question/3663
 */
public class _114ImageEncryption {


    public static void main(String[] args) {
        _114ImageEncryption sln = new _114ImageEncryption();

        Scanner scanner = new Scanner(System.in);
        int nTestCases = scanner.nextInt();
        for (int testCase = 0; testCase < nTestCases; testCase++) {
            int n = scanner.nextInt();
            int[][] m1 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    m1[i][j] = scanner.nextInt();
                }
            }
            int[][] m2 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    m2[i][j] = scanner.nextInt();
                }
            }
            System.out.println(sln.compare(sln.dfs(m1), sln.dfs(m2)) == 0 ? "Yes" : "No");
            System.out.println(Arrays.deepToString(sln.dfs(m1)));
            System.out.println(Arrays.deepToString(sln.dfs(m2)));
        }
    }

    int[][] dfs(int[][] matrix) {
        int n = matrix.length;
        if ((n & 0x1) == 0x1) { // odd
            int[][] min = matrix;
            for (int i = 0; i < 3; i++) {
                matrix = rotate(matrix);
                if (compare(matrix, min) < 0) {
                    min = matrix;
                }
            }
            return min;
        } else {
            int[][] m1 = dfs(subMatrixLT(matrix));
            int[][] m2 = dfs(subMatrixRT(matrix));
            int[][] m3 = dfs(subMatrixRB(matrix));
            int[][] m4 = dfs(subMatrixLB(matrix));
            matrix = mergeHalfMatrix(m1, m2, m3, m4);
            int[][] min = matrix;
            for (int i = 0; i < 3; i++) {
                matrix = rotateHalf(matrix);
                if (compare(matrix, min) < 0) {
                    min = matrix;
                }
            }
            return min;
        }
    }

    private int[][] subMatrixRB(int[][] matrix) {
        int n2 = matrix.length >> 1;
        int[][] subMatrix = new int[n2][n2];
        for (int i = 0; i < n2; i++) {
            System.arraycopy(matrix[i + n2], n2, subMatrix[i], 0, n2);
        }
        return subMatrix;
    }

    private int[][] subMatrixLB(int[][] matrix) {
        int n2 = matrix.length >> 1;
        int[][] subMatrix = new int[n2][n2];
        for (int i = 0; i < n2; i++) {
            System.arraycopy(matrix[i + n2], 0, subMatrix[i], 0, n2);
        }
        return subMatrix;
    }

    private int[][] subMatrixRT(int[][] matrix) {
        int n2 = matrix.length >> 1;
        int[][] subMatrix = new int[n2][n2];
        for (int i = 0; i < n2; i++) {
            System.arraycopy(matrix[i], n2, subMatrix[i], 0, n2);
        }
        return subMatrix;
    }

    private int[][] subMatrixLT(int[][] matrix) {
        int n2 = matrix.length >> 1;
        int[][] subMatrix = new int[n2][n2];
        for (int i = 0; i < n2; i++) {
            System.arraycopy(matrix[i], 0, subMatrix[i], 0, n2);
        }
        return subMatrix;
    }

    private int compare(int[][] m1, int[][] m2) {
        int n = m1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m1[i][j] < m2[i][j])
                    return -1;
                else if (m1[i][j] > m2[i][j])
                    return 1;
            }
        }
        return 0;
    }

    private int[][] mergeHalfMatrix(int[][] m1, int[][] m2, int[][] m3, int[][] m4) {
        int n2 = m1.length;
        int n = m1.length << 1;
        int[][] merged = new int[n][n];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
                merged[i][j] = m1[i][j];
                merged[i][j + n2] = m2[i][j];
                merged[i + n2][j + n2] = m3[i][j];
                merged[i + n2][j] = m4[i][j];
            }
        }
        return merged;
    }

    private int[][] rotateHalf(int[][] m) {
        int[][] m1 = subMatrixLT(m);
        int[][] m2 = subMatrixRT(m);
        int[][] m3 = subMatrixRB(m);
        int[][] m4 = subMatrixLB(m);
        return mergeHalfMatrix(m4, m1, m2, m3);
    }

    private int[][] rotate(int[][] m) {
        int n = m.length;
        int[][] rotated = new int[n][n];
        for (int layer = 0, n2 = n >> 1; layer <= n2; ++layer) {
            int boundUpper = n - 1 - layer;
            for (int i = 0; i <= boundUpper - layer; ++i) {
                int floatLower = layer + i;
                int floatUpper = boundUpper - i;
                rotated[layer][floatUpper] = m[floatLower][layer];
                rotated[floatLower][layer] = m[boundUpper][floatLower];
                rotated[boundUpper][floatLower] = m[floatUpper][boundUpper];
                rotated[floatUpper][boundUpper] = m[layer][floatUpper];
            }
        }
        return rotated;
    }

    @Test
    public void test() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};


        System.out.println(compare(new int[][]{{2, 4}, {1, 3}}, new int[][]{{3, 1}, {4, 2}}));

//        System.out.println(Arrays.deepToString(rotateMatrix(new int[][]{{1}})));
//        System.out.println(Arrays.deepToString(rotateMatrix(new int[][]{{1, 2}, {4, 3}})));
//        System.out.println(Arrays.deepToString(rotateMatrix(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}})));
        System.out.println(Arrays.deepToString(subMatrixLT(matrix)));
        System.out.println(Arrays.deepToString(subMatrixRT(matrix)));
        System.out.println(Arrays.deepToString(subMatrixRB(matrix)));
        System.out.println(Arrays.deepToString(subMatrixLB(matrix)));
        System.out.println(Arrays.deepToString(rotateHalf(matrix)));
    }
}
