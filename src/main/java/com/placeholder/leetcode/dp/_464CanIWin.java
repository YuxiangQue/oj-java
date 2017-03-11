package com.placeholder.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class _464CanIWin {

    static boolean canIWin(int k, int n) {
        if (n == 0) {
            return true;
        }
        if ((1 + k) * k / 2 < n) {
            return false;
        }
        int chosen = 0;
        Map<Integer, Boolean> memorize = new HashMap<>();
        return canIWin(k, n, chosen, 0, memorize);
    }

    static boolean canIWin(int k, int n, int chosen, int sumChosen, Map<Integer, Boolean> memorize) {
        if (memorize.getOrDefault(chosen, false)) {
            return true;
        }
        if (sumChosen >= n) {
            memorize.put(chosen, false);
            return false;
        }
        for (int i = 1; i <= k; ++i) {
            int bit = 1 << i;
            if ((chosen & bit) == 0) {
                chosen ^= bit;
                boolean sheWin = canIWin(k, n, chosen, sumChosen + i, memorize);
                chosen ^= bit;
                if (!sheWin) {
                    memorize.put(chosen, true);
                    return true;
                }
            }
        }
        memorize.put(chosen, false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canIWin(10, 40));// false
        System.out.println(canIWin(11, 25));// true
        System.out.println(canIWin(18, 79));// true
    }
}
