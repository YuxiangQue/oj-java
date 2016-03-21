package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _112PathSum {
    boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        // leaf node
        if (root.left == null && root.right == null && sum - root.val == 0)
            return true;
        if (root.left != null && hasPathSum(root.left, sum - root.val)) {
            return true;
        }
        if (root.right != null && hasPathSum(root.right, sum - root.val)) {
            return true;
        }
        return false;
    }
}
