package com.placeholder.leetcode.tree.dfs;

import com.placeholder.builtin.TreeNode;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _101SymmetricTree {
    boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val
                && dfs(left.left, right.right)
                && dfs(left.right, right.left);
    }

    boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return dfs(root.left, root.right);
    }
}
