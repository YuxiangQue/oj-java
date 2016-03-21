package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _110BalancedBinaryTree {
    boolean _isBalanced(TreeNode root, int[] depth) {
        if (null == root) {
            depth[0] = 0;
            return true;
        }
        int[] ldepth = new int[1];
        int[] rdepth = new int[1];
        if (!_isBalanced(root.left, ldepth) || !_isBalanced(root.right, rdepth))
            return false;
        else {
            int diff = ldepth[0] - rdepth[0];
            depth[0] = ldepth[0] > rdepth[0] ? ldepth[0] + 1 : rdepth[0] + 1;
            return (diff > -2 && diff < 2);
        }
    }

    boolean isBalanced(TreeNode root) {
        int[] depth = new int[1];
        return _isBalanced(root, depth);
    }
}
