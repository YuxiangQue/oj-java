package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _98ValidateBinarySearchTree {

    boolean helper(TreeNode root, int[] min, int[] max) {
        int[] leftMin = new int[1];
        int[] leftMax = new int[1];
        int[] rightMin = new int[1];
        int[] rightMax = new int[1];

        // left
        if (root.left != null) {
            if (!helper(root.left, leftMin, leftMax)) {
                return false;
            }
        } else {
            leftMax[0] = Integer.MIN_VALUE;
        }

        // right
        if (root.right != null) {
            if (!helper(root.right, rightMin, rightMax)) {
                return false;
            }
        } else {
            rightMin[0] = Integer.MAX_VALUE;
        }

        //
        if (leftMax[0] >= root.val)
            return false;
        if (rightMin[0] <= root.val)
            return false;
        min[0] = leftMin[0];
        max[0] = rightMax[0];
        return true;

    }

    boolean isValidBST(TreeNode root) {
        int[] minVal = new int[1];
        int[] maxVal = new int[1];
        return helper(root, minVal, maxVal);
    }


}
