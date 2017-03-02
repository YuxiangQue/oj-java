package com.placeholder.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _491IncreasingSubsequence {

    static void dfs(int[] nums, int prevIndex, int index, List<Integer> holder, List<List<Integer>> items) {
        if (index == nums.length) {
            if (holder.size() >= 2) {
                items.add(new ArrayList<>(holder));
            }
            return;
        }

        if (prevIndex == -1 || nums[prevIndex] <= nums[index]) {
            holder.add(nums[index]);
            dfs(nums, index, index + 1, holder, items);
            holder.remove(holder.size() - 1);
        }

        dfs(nums, prevIndex, index + 1, holder, items);
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> items = new ArrayList<>();
        dfs(nums, -1, 0, new ArrayList<>(), items);

        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> duplicateSet = new HashSet<>();
        for (List<Integer> item : items) {
            if (!duplicateSet.contains(item)) {
                duplicateSet.add(item);
                ans.add(item);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        findSubsequences(new int[]{4, 6, 7, 7});
    }
}
