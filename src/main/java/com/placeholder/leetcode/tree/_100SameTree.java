package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

/**
 * https://leetcode.com/problems/same-tree/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _100SameTree {
    boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null)
            return false;
        if (p != null && q == null)
            return false;
        if (p == null && q == null)
            return true;
        if (p.val != q.val)
            return false;
        boolean isLeftSame = isSameTree(p.left, q.left);
        boolean isRightSame = isSameTree(p.right, q.right);
        return isLeftSame && isRightSame;
    }
}
