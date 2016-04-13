package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _78Subsets {

    public static void main(String[] args) {
        List<List<Integer>> result = subsets(new int[]{1, 2, 3});
        System.out.println(result);
        return;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs2(nums, 0, new ArrayList<>(), result);
        return result;
    }

    // [1 2 3]
    // 1. [] [1] [1 2] [1 2 3]
    // 2.        [1 3]
    // 3. [2] [2 3]
    // 4. [3]
    private static void dfs2(int[] candidates, int index, List<Integer> item, List<List<Integer>> result) {
        result.add(new ArrayList<>(item));
        for (int i = index; i < candidates.length; ++i) {
            item.add(candidates[i]);  // add candidate
            dfs2(candidates, i + 1, item, result);
            item.remove(item.size() - 1); // remove candidate
        }
    }

    private static void dfs(int[] candidates, int index, List<Integer> item, List<List<Integer>> result) {
        if (index == candidates.length) {
            result.add(new ArrayList<>(item));
            return;
        }

        // the candidate
        item.add(candidates[index]);
        dfs(candidates, index + 1, item, result);
        item.remove(item.size() - 1);

        // skip candidate
        dfs(candidates, index + 1, item, result);
    }
}