package com.placeholder.leetcode.backtracking;

import com.placeholder.common.Ints;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * http://blog.csdn.net/linhuanmars/article/details/21569031
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _46Permutations {

    public static void main(String[] args) {
        NQueens NQueens = new NQueens();
        System.out.println(NQueens.permute(new int[]{1, 2, 3}));
    }

    static class Waste {
        private static ArrayList<Integer> asList(int[] nums) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(i, nums[i]);
            }
            return list;
        }

        public List<List<Integer>> permute(int[] nums) {
            List<Integer> item = new ArrayList<>();
            List<List<Integer>> result = new ArrayList<>();
            helper(asList(nums), item, result);
            return result;
        }

        /**
         * @param candidates 可用的
         * @param item       当前组合
         * @param result     所有组合
         */
        private void helper(List<Integer> candidates, List<Integer> item, List<List<Integer>> result) {
            if (candidates.size() == 0) {
                result.add(item);
                return;
            }
            int length = candidates.size();
            for (int i = 0; i < length; ++i) {
                // 这里浪费了资源
                List<Integer> candidates1 = new ArrayList<>(candidates);
                List<Integer> item1 = new ArrayList<>(item);
                item1.add(candidates1.remove(i));
                helper(candidates1, item1, result);
            }
        }
    }

    static class NQueens {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length == 0)
                return res;
            helper(Ints.asList(nums), new ArrayList<>(), res);
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
                int tmp = candidates.remove(i);
                item.add(tmp);
                helper(candidates, item, res);
                item.remove(item.size() - 1);
                candidates.add(i, tmp);
            }
        }
    }
}
