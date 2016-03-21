package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _77Combinations {
    public static void main(String[] args) {
        MethodNQueens m = new MethodNQueens();
        List<List<Integer>> result = m.combine(4, 2);
        System.out.println(result);
    }

    static class MethodNQueens {

        int k;
        int n;
        int[] candidates = null;
        List<Integer> group = null;
        List<List<Integer>> groups = null;


        public List<List<Integer>> combine(int n, int k) {
            this.k = k;
            this.n = n;
            candidates = new int[n];
            for (int i = 0; i < n; ++i) {
                candidates[i] = i + 1;
            }
            group = new ArrayList<>();
            groups = new ArrayList<>();
            helper(0);
            return groups;
        }


        private void helper(int lastIndex) {
            int length = candidates.length;
            // if (group.size() == k) {
            groups.add(new ArrayList<>(group));
            // return;
            //   }
            if (group.size() >= k) {
                return;
            }
            for (int i = lastIndex; i < length; ++i) {
                group.add(candidates[i]);
                helper(i + 1);
                group.remove(group.size() - 1);
            }
        }
    }
}
