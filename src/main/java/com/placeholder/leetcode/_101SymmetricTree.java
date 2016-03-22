package com.placeholder.leetcode;

import com.placeholder.common.TreeNode;

/**
 * @author yuxiangque
 * @version 2016/3/18
 */
public class _101SymmetricTree {
    boolean _isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val
                && _isSymmetric(left.left, right.right)
                && _isSymmetric(left.right, right.left);
    }

    boolean isSymmetric(TreeNode root) {
        return root == null || _isSymmetric(root.left, root.right);
    }
}
