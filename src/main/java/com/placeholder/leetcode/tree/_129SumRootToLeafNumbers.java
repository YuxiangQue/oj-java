package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _129SumRootToLeafNumbers {
    void sumNumbers(TreeNode root, List<Integer> path, List<List<Integer>> paths) {
        if (root == null)
            return;
        // leaf node
        if (root.left == null && root.right == null) {
            path.add(root.val);
            paths.add(path);
            path.remove(path.size() - 1);
        }
        if (root.left != null) {
            path.add(root.val);
            sumNumbers(root.left, path, paths);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.val);
            sumNumbers(root.right, path, paths);
            path.remove(path.size() - 1);
        }
    }

    int sumNumbers(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();
        sumNumbers(root, new ArrayList<>(), paths);
        int sum = 0;
        for (List<Integer> path : paths) {
            long num = 0;
            for (Integer digit : path) {
                num += digit;
                num *= 10;
            }
            num /= 10;
            sum += num;
        }
        return sum;
    }
}
