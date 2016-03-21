package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * http://www.tuicool.com/articles/2AJ3Uf
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _47Permutations2 {
    public static void main(String[] args) {
        MethodNQueens methodNQueens = new MethodNQueens();
        System.out.println(methodNQueens.permuteUnique(new int[]{-1, 2, -1, 2, 1, -1, 2, 1}));

    }

    static class MethodNQueens {

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0)
                return res;
            Arrays.sort(nums);
            helper(nums, new boolean[nums.length], new ArrayList<>(), res);
            return res;
        }

        private void helper(int[] nums, boolean[] used, List<Integer> item, List<List<Integer>> res) {
            int length = nums.length;
            if (item.size() == length) {
                res.add(new ArrayList<>(item));
                return;
            }
            for (int i = 0; i < length; i++) {
                if (!used[i]) {
                    if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) // TODO: ???
                        continue;
                    used[i] = true;
                    item.add(nums[i]);
                    helper(nums, used, item, res);
                    item.remove(item.size() - 1);
                    used[i] = false;
                }
            }
        }
    }

    static class MethodNQueens1 {
        private static ArrayList<Integer> asList(int[] nums) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(i, nums[i]);
            }
            return list;
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0)
                return res;
            Arrays.sort(nums);
            helper(asList(nums), new ArrayList<>(), res);
            return res;
        }

        /**
         * @param candidates 可用的
         * @param item       持有当前组合
         * @param res        所有组合
         */
        private void helper(List<Integer> candidates, List<Integer> item, List<List<Integer>> res) {
            if (candidates.size() == 0) {
                res.add(new ArrayList<>(item));
                return;
            }
            int length = candidates.size();
            for (int i = 0; i < length; i++) {
                if (i > 0 && Objects.equals(candidates.get(i), candidates.get(i - 1)))
                    continue;
                int tmp = candidates.remove(i);
                item.add(tmp);
                helper(candidates, item, res);
                item.remove(item.size() - 1);
                candidates.add(i, tmp);
            }
        }
    }
}
