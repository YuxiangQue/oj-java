package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _230KthSmallestElementInABST {
    void kthSmallest(TreeNode root, int[] k, int[] value) {
        if (root == null)
            return;
        if (k[0] <= 0)
            return;

        kthSmallest(root.left, k, value);

        k[0] -= 1;
        if (k[0] == 0) {
            value[0] = root.val;
            return;
        }

        kthSmallest(root.right, k, value);
    }

    int kthSmallest(TreeNode root, int k) {
        int[] value = new int[1];
        kthSmallest(root, new int[1], value);
        return value[0];
    }
}
