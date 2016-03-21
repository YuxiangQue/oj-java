package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _104MaximumDepthOfBinaryTree {
    int maxDepth(TreeNode root) {
        if (null == root)
            return 0;
        else {
            int l = maxDepth(root.left);
            int r = maxDepth(root.right);
            return l > r ? l + 1 : r + 1;
        }
    }
}
