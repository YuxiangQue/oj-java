package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _236LowestCommonAncestorOfABinaryTree {
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }
}
