package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * http://blog.csdn.net/linhuanmars/article/details/20828631
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _39CombinationSum {

    public static void main(String[] args) {
        MethodNQueens m = new MethodNQueens();
        List<List<Integer>> result = m.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(result);
    }

    static class MethodNQueens {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (candidates.length == 0)
                return result;
            Arrays.sort(candidates);
            dfs(candidates, target, 0, new ArrayList<>(), result);
            return result;
        }

        private void dfs(int[] candidates, int target, int lastIndex, List<Integer> item, List<List<Integer>> result) {
            int length = candidates.length;
            if (target == 0) {
                result.add(new ArrayList<>(item));
                return;
            }
            if (target < 0) {
                return;
            }
            for (int i = lastIndex; i < length; ++i) {
                item.add(candidates[i]);
                dfs(candidates, target - candidates[i], i, item, result);
                item.remove(item.size() - 1);
            }
        }
    }
}
