package com.placeholder.test.mstest2016april1;

import java.util.Scanner;

/**
 * @author yuxiangque
 * @version 2016/4/18
 */
public class FontSize {

    public static int fontSize(int P, int W, int H, int[] a) {
        int emptyLines = Integer.MAX_VALUE;  // 剩余空行数目
        int fontSize = -1;
        int maxS = Math.min(H, W);  // 1 character at least
        for (int S = maxS; S >= 1; --S) {
            int maxNumOfLines = P * (H / S);
            int numOfLines = 0;
            for (int i = 0; i < a.length; i++) {
                numOfLines += Math.ceil(a[i] / (W / S));  //  show ⌊W / S⌋ characters in a line
                if (numOfLines > maxNumOfLines) {
                    break;
                }
            }
            if (numOfLines > maxNumOfLines) {
                continue;
            }
            int empty = maxNumOfLines - numOfLines;
            if (empty < emptyLines) {
                emptyLines = empty;
                fontSize = S;
            }
        }
        return fontSize;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TASKS = scanner.nextInt();
        for (int i = 0; i < TASKS; i++) {
            int N = scanner.nextInt();
            int P = scanner.nextInt();
            int W = scanner.nextInt();
            int H = scanner.nextInt();
            int[] a = new int[N];
            for (int j = 0; j < N; j++) {
                a[j] = scanner.nextInt();
            }
            System.out.println(fontSize(P, W, H, a));
        }
    }
}
