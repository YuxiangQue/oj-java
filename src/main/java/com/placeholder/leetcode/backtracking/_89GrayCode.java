package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/gray-code/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _89GrayCode {
    public static void main(String[] args) {
        MethodNQueens methodNQueens = new MethodNQueens();
        List<Integer> result = methodNQueens.grayCode(2);
        System.out.println(result);
    }

    static class MethodNQueens {
        public List<Integer> grayCode(int n) {
            int[] item = new int[n];
            List<Integer> result = new ArrayList<>();
            dfs(item, 0, result);
            return result;
        }

        public void dfs(int[] bitset, int index, List<Integer> result) {
            int n = bitset.length;
            if (index == n) {
                int value = 0;
                for (int i = 0; i < n; ++i) {
                    value = value << 1;
                    value |= bitset[i];
                }
                result.add(value);
                return;
            }
            dfs(bitset, index + 1, result);
            bitset[index] = bitset[index] == 1 ? 0 : 1;
            dfs(bitset, index + 1, result);
        }
    }
}
