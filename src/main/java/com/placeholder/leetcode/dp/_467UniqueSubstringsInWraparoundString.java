package com.placeholder.leetcode.dp;

import java.util.stream.IntStream;

public class _467UniqueSubstringsInWraparoundString {

    public static int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int length = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i >= 1 && (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i) - p.charAt(i - 1) + 26 == 1)) {
                ++length;
            } else {
                length = 1;
            }
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], length);
        }
        return IntStream.of(count).sum();
    }
}
