package com.placeholder.leetcode;

/**
 * https://discuss.leetcode.com/topic/1344/share-some-of-my-ideas
 */
public class _134GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int remainGas = 0;
        int start = 0;
        for (int i = 0, len = gas.length; i < len; i++) {
            remainGas += gas[i] - cost[i];
            totalGas += remainGas;
            if (remainGas < 0) {
                remainGas = 0;
                start = i + 1;
            }
        }
        return totalGas < 0 ? -1 : start;
    }

    public static void main(String[] args) {

    }
}
