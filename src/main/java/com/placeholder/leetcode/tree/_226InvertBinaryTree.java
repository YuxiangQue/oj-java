package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _226InvertBinaryTree {
    void swapTreeNode(TreeNode node) {
        if (node == null)
            return;
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        swapTreeNode(node.left);
        swapTreeNode(node.right);
    }

    TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        swapTreeNode(root);
        return root;
    }
}
