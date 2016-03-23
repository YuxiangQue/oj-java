package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 * https://leetcode.com/problems/subsets/
 *
 * @author yuxiangque
 * @version 2016/3/23
 */
public class _90Subset2 {
    public static void main(String[] args) {
        List<List<Integer>> result = subsetsWithDup(new int[]{1, 2, 2});
        return;
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }

    // [1 2 2]
    // 1. [] [1] [1 2] [1 2 2](x)
    // 2.        [1 2](x)
    // 3.    [2] [2 2]
    // 4.    [2](x)
    private static void dfs(int[] candidates, int index, List<Integer> item, List<List<Integer>> result) {
        result.add(new ArrayList<>(item));
        for (int i = index; i < candidates.length; ++i) {
            if (i != index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            item.add(candidates[i]);  // add candidate
            dfs(candidates, i + 1, item, result);
            item.remove(item.size() - 1); // remove candidate
        }
    }
}
