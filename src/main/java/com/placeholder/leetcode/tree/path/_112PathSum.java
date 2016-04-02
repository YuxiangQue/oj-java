package com.placeholder.leetcode.tree.path;

import com.placeholder.common.TreeNode;

/**
 * #dfs
 * https://leetcode.com/problems/path-sum/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _112PathSum {
    boolean dfs(TreeNode root, int sum) {
        if (root == null)
            return false;
        // leaf node
        if (root.left == null && root.right == null && sum - root.val == 0)
            return true;
        if (root.left != null && dfs(root.left, sum - root.val)) {
            return true;
        }
        if (root.right != null && dfs(root.right, sum - root.val)) {
            return true;
        }
        return false;
    }
}
