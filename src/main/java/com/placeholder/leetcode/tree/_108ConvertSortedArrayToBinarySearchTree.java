package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _108ConvertSortedArrayToBinarySearchTree {
    private TreeNode helper(List<Integer> num, int begin, int end) {
        if (begin > end)
            return null;
        int mid = (end + begin) / 2;
        TreeNode root = new TreeNode(num.get(mid));
        root.left = helper(num, begin, mid - 1);
        root.right = helper(num, mid + 1, end);
        return root;
    }

    public TreeNode sortedArrayToBST(List<Integer> num) {
        return helper(num, 0, num.size() - 1);
    }
}
