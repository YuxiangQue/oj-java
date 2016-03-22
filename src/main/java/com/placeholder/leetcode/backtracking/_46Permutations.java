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
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        dfs(Ints.asList(nums), new ArrayList<>(), res);
        return res;
    }

    /**
     * @param candidates 可用的
     * @param item       持有当前组合
     * @param res        所有组合
     */
    private static void dfs(List<Integer> candidates, List<Integer> item, List<List<Integer>> res) {
        if (candidates.size() == 0) {
            res.add(new ArrayList<>(item));
            return;
        }
        int length = candidates.size();
        for (int i = 0; i < length; i++) {
            int tmp = candidates.remove(i);
            item.add(tmp);
            dfs(candidates, item, res);
            item.remove(item.size() - 1);
            candidates.add(i, tmp);
        }
    }
}
