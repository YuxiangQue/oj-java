package com.placeholder.hihoCoder;

import java.util.Arrays;
import java.util.Scanner;

public class _1504 {

    static final int S = 8;
    static final int MOD = 1000000007;
    static int[][] dirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    private static long[] mulMatrixVec(long[][] mat, long[] vec) {
        int n1 = mat.length;
        int n2 = mat[0].length;
        long[] vec2 = new long[n2];
        for (int i = 0; i < n1; ++i) {
            for (int j = 0; j < n2; ++j) {
                vec2[i] = (vec2[i] + mat[i][j] * vec[j]) % MOD;
            }
        }
        return vec2;
    }

    private static long[][] mulMatrix(long[][] mat1, long[][] mat2) {
        int n1 = mat1.length;
        int n2 = mat1[0].length;
        int n3 = mat2[0].length;
        long[][] mat3 = new long[n1][n3];
        for (int k = 0; k < n2; ++k) {
            for (int i = 0; i < n1; ++i) {
                for (int j = 0; j < n3; ++j) {
                    mat3[i][j] = (mat3[i][j] + mat1[i][k] * mat2[k][j] % MOD) % MOD;
                }
            }
        }
        return mat3;
    }

    private static long[][] powMatrix(long[][] mat, long k) {
        if (k == 1) {
            return mat;
        }
        if ((k & 1) == 1) {
            long[][] temp = powMatrix(mat, k >> 1);
            temp = mulMatrix(temp, temp);
            return mulMatrix(mat, temp);
        } else {
            long[][] temp = powMatrix(mat, k >> 1);
            return mulMatrix(temp, temp);
        }
    }

    public static long solveMatrix(int N, int R, int C) {
        long[][] mat = new long[S * S][S * S];
        long[] nums = new long[S * S];
        Arrays.fill(nums, 1);
        for (int r = 0; r < S; r++) {
            for (int c = 0; c < S; c++) {
                mat[r * S + c][r * S + c] = 0;
                for (int[] d : dirs) {
                    if (r + d[0] >= 0 && r + d[0] < S && c + d[1] >= 0 && c + d[1] < S) {
                        mat[r * S + c][r * S + c + d[0] * S + d[1]] = 1;
                    }
                }
            }
        }
        mat = powMatrix(mat, N);
        nums = mulMatrixVec(mat, nums);
        return nums[R * S + C];
    }

    public static int solveDp(int N, int R, int C) {
        int[] dp = new int[S * S];
        int[] temp = new int[S * S];
        for (int i = 0; i < S * S; ++i) {
            dp[i] = 1;
        }
        for (int k = 1; k <= N; ++k) {
            for (int i = 0; i < S * S; ++i) {
                temp[i] = dp[i];
            }
            for (int i = 0; i < S * S; ++i) {
                int r = i / S, c = i % S;
                int num = 0;
                for (int[] d : dirs) {
                    if (r + d[0] >= 0 && r + d[0] < S && c + d[1] >= 0 && c + d[1] < S) {
                        num = (num + temp[S * r + c + S * d[0] + d[1]]) % MOD;
                    }
                }
                dp[i] = num;
            }
        }
        return dp[R * S + C];
    }

    private static long solveDfsHelper(int n, int r, int c, long[][][] memorize) {
        if (r < 0 || c < 0 || r >= S || c >= S) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (memorize[n][r][c] != -1) {
            return memorize[n][r][c];
        }
        long num = 0;
        for (int[] d : dirs) {
            num = (num + solveDfsHelper(n - 1, r + d[0], c + d[1], memorize)) % MOD;
        }
        memorize[n][r][c] = num;
        return num;
    }

    public static long solveDfs(int n, int r, int c) {
        long[][][] memorize = new long[n + 1][S][S];
        for (int k = 0; k <= n; k++) {
            for (int i = 0; i < S; i++) {
                for (int j = 0; j < S; j++) {
                    memorize[k][i][j] = -1;
                }
            }
        }
        long num = solveDfsHelper(n, r, c, memorize);
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        System.out.println(solveMatrix(n, r - 1, c - 1));
//        System.out.println(solveDp(n, r - 1, c - 1));
//        System.out.println(solveDfs(n, r - 1, c - 1));
    }
}
