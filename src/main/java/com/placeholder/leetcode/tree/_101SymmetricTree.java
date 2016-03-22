package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _101SymmetricTree {
    boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val
                && helper(left.left, right.right)
                && helper(left.right, right.left);
    }

    boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }
}
