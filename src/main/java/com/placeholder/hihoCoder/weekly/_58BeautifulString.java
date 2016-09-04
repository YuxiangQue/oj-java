package com.placeholder.hihoCoder.weekly;

import java.util.Scanner;

/**
 * http://hihocoder.com/problemset/problem/1061
 */
public class _58BeautifulString {

    public static boolean beautifulString(String s) {
        int index = 0;
        int len = s.length();
        char[] chars = s.toCharArray();
        char[] c = new char[len];
        int[] n = new int[len];
        for (int j = 0; j < chars.length; j++) {
            if (j == 0) {
                c[index] = chars[0];
                n[index] = 1;
            } else {
                if (c[index] == chars[j]) {
                    n[index] += 1;
                } else {
                    index += 1;
                    c[index] = chars[j];
                    n[index] = 1;
                }
            }
        }
        for (int i = 1; i <= index - 1; i++) {
            if (n[i - 1] >= n[i] && n[i + 1] >= n[i] &&
                    c[i] + 1 == c[i + 1] && c[i - 1] + 1 == c[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfCases = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numOfCases; i++) {
            scanner.nextLine();
            System.out.println(beautifulString(scanner.nextLine()) ? "YES" : "NO");
        }
    }
}
