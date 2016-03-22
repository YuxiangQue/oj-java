package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _40CombinationSum2 {

    public static void main(String[] args) {
        MethodNQueens m = new MethodNQueens();
        List<List<Integer>> result = m.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(result);
    }

    static class MethodNQueens {

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (candidates.length == 0)
                return result;
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>(), result);
            return result;
        }

        private void dfs(int[] candidates, int target, int lastIndex, List<Integer> item, List<List<Integer>> result) {
            if (target < 0)
                return;
            int length = candidates.length;
            if (target == 0) {
                result.add(new ArrayList<>(item));
                return;
            }
            for (int i = lastIndex; i < length; ++i) {
                if (i > lastIndex && candidates[i] == candidates[i - 1])
                    continue;
                item.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, item, result);
                item.remove(item.size() - 1);
            }
        }
    }

}
