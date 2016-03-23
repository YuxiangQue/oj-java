package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _216CombinationSum3 {
    public static void main(String[] args) {
        MethodNQueens m = new MethodNQueens();
        List<List<Integer>> result = m.combinationSum3(3, 7);
        System.out.println(result);
        List<List<Integer>> result1 = m.combinationSum3(3, 9);
        System.out.println(result1);
        List<List<Integer>> result2 = m.combinationSum3(2, 18);
        System.out.println(result2);
    }

    static class MethodNQueens {

        int k;
        int n;
        int[] candidates = null;
        List<Integer> group = null;
        List<List<Integer>> groups = null;


        public List<List<Integer>> combinationSum3(int k, int n) {
            this.k = k;
            this.n = n;
            candidates = new int[9];
            for (int i = 0; i < 9; ++i) {
                candidates[i] = i + 1;
            }
            group = new ArrayList<>();
            groups = new ArrayList<>();
            dfs(0);
            return groups;
        }

        public int sum(List<Integer> nums) {
            int sum = 0;
            for (Integer num : nums) {
                sum += num;
            }
            return sum;
        }

        private void dfs(int lastIndex) {
            int length = candidates.length;
            if (group.size() == k && sum(group) == n) {
                groups.add(new ArrayList<>(group));
                return;
            }
            if (group.size() >= k) {
                return;
            }
            if (sum(group) > n) {
                return;
            }
            for (int i = lastIndex; i < length; ++i) {
                group.add(candidates[i]);
                dfs(i + 1);
                group.remove(group.size() - 1);
            }
        }
    }
}
