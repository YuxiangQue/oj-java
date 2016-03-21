package com.placeholder.hihoCoder.microsoft;

import java.util.Scanner;

/**
 * http://hihocoder.com/discuss/question/2839/
 * Created by Joyce on 2016/3/9.
 */
public class _88Coordinates {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        int[] pDivisors = new int[p];
        int[] qDivisors = new int[q];
        int pCount = 0;
        int qCount = 0;
        for (int i = 1; i <= p; i++) {
            if (p % i == 0) {
                pDivisors[pCount++] = i;
            }
        }
        for (int i = 1; i <= q; i++) {
            if (q % i == 0) {
                qDivisors[qCount++] = i;
            }
        }
        for (int i = 0; i < pCount; i++) {
            for (int j = 0; j < qCount; j++) {
                System.out.println(pDivisors[i] + " " + qDivisors[j]);
            }
        }
    }
}
