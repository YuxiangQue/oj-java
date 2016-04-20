package com.placeholder.jianzhioffer;

import java.util.Arrays;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class MultiplyArray {
    public static int[] multiply(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int n = A.length;
        int[] L = new int[n];
        int[] R = new int[n];
        int[] B = new int[n];

        L[0] = A[0];
        for (int i = 1; i < n; ++i) {
            L[i] = L[i - 1] * A[i];
        }

        R[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            R[i] = R[i + 1] * A[i];
        }

        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                B[i] = R[i + 1];
            } else if (i == n - 1) {
                B[i] = L[i - 1];
            } else {
                B[i] = L[i - 1] * R[i + 1];
            }
        }
        return B;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(multiply(new int[]{1, 2, 3, 4, 5})));
    }
}
