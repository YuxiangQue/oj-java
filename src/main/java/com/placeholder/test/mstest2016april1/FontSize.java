package com.placeholder.test.mstest2016april1;

import java.util.Scanner;

public class FontSize {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tasks = scanner.nextInt();
        for (int i = 0; i < tasks; i++) {
            int n = scanner.nextInt();
            int p = scanner.nextInt();
            int w = scanner.nextInt();
            int h = scanner.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = scanner.nextInt();
            }
            System.out.println(fontSize(n, p, w, h, a));
        }
    }

    private static int fontSize(int n, int p, int w, int h, int[] a) {
        // w/s h/s
        int maxS = Math.min(w, h);
        for (int s = maxS; s >= 1; --s) {
            int maxLines = p * (h / s);
            int maxChars = w / s;
            for (int i = 0; i < n; i++) {
                int lines = a[i] / maxChars + (a[i] % maxChars == 0 ? 0 : 1);
                maxLines -= lines;
            }
            if (maxLines >= 0) {
                return s;
            }
        }
        return 0;
    }
}