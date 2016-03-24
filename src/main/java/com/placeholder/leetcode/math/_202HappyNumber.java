package com.placeholder.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _202HappyNumber {
    public static boolean isHappy(int n) {
        Map<Integer, Boolean> deadCycle = new HashMap<>();
        while (true) {
            int m = n;
            int sum = 0;
            while (true) {
                int q = m % 10;
                m = m / 10;
                sum += q * q;
                if (m == 0) {
                    break;
                }
            }
            if (deadCycle.containsKey(sum)) {
                return false; // dead cycle
            } else {
                deadCycle.put(sum, true);
                n = sum;
                if (n == 1)
                    return true;
            }
        }
    }
}
