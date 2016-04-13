package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _77Combinations {
    public static void main(String[] args) {
        List<List<Integer>> result = combine(4, 2);
        System.out.println(result);
    }

    public static List<List<Integer>> combine(int n, int k) {
        int[] candidates = new int[n];
        for (int i = 0; i < n; ++i) {
            candidates[i] = i + 1;
        }
        List<List<Integer>> results = new ArrayList<>();
        dfs(k, candidates, 0, new ArrayList<>(), results);
        return results;
    }

    private static void dfs(int k, int[] candidates, int lastIndex, List<Integer> item, List<List<Integer>> results) {
        int length = candidates.length;
        results.add(new ArrayList<>(item));
        if (item.size() >= k) {
            return;
        }
        for (int i = lastIndex; i < length; ++i) {
            item.add(candidates[i]);
            dfs(k, candidates, i + 1, item, results);
            item.remove(item.size() - 1);
        }
    }
}
