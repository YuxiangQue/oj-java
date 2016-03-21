package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _114FlattenBinaryTreeToLinkedList {
    void helper(TreeNode root, List<TreeNode> link) {
        if (root != null) {
            link.add(root);
            helper(root.left, link);
            helper(root.right, link);
        }
    }

    void flatten(TreeNode root) {
        if (root == null)
            return;
        List<TreeNode> link = new ArrayList<>();
        helper(root, link);
        int i = 0, n = link.size();
        for (; i < n - 1; ++i) {
            link.get(i).left = null;
            link.get(i).right = link.get(i + 1);
        }
        link.get(n - 1).left = null;
        link.get(n - 1).right = null;
    }
}
