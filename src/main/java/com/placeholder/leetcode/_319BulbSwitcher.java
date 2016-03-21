package com.placeholder.leetcode;

/**
 * @author 阙宇翔
 * @version 2016/2/24
 */
public class _319BulbSwitcher {
    public static void main(String[] args) {
        new _319BulbSwitcher().bulbSwitch(3);
    }

    public int bulbSwitch(int n) {
        boolean[] bulbs = new boolean[n];
        for (int round = 1; round <= n; ++round) {
            for (int i = round - 1; i < n; i += round) {
                bulbs[i] = !bulbs[i];
            }
        }
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (bulbs[i])
                ++count;
        }
        return count;
    }

    // https://leetcode.com/discuss/75014/math-solution
    public int bulbSwitch1(int n) {
        return (int) Math.sqrt(n);
    }
}
