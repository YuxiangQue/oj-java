package com.placeholder.common;

import java.util.Scanner;

public class MaxSumOfRectangle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int te = scanner.nextInt();
        for (int k = 0; k < te; k++) {
            int n = scanner.nextInt(), m = scanner.nextInt();
            int[][] rect = new int[n][m];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    rect[x][y] = scanner.nextInt();
                }
            }
            System.out.println(maxSubMatrix(rect));
        }
    }

    private static int maxSubArray(int arr[]) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int num : arr) {
            sum += num;
            if (sum > maxSum)
                maxSum = sum;
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }

    private static int maxSubMatrix(int[][] rect) {
        int n = rect.length, m = rect[0].length;
        int max = Integer.MIN_VALUE;
        for (int x1 = 0; x1 < n; x1++) {
            int rowSum[] = new int[m];
            for (int x2 = x1; x2 < n; x2++) {
                for (int y = 0; y < m; y++) {
                    rowSum[y] += rect[x2][y];
                }
                int runningMax = maxSubArray(rowSum);
                if (runningMax > max) {
                    max = runningMax;
                }
            }
        }
        return max;
    }
}