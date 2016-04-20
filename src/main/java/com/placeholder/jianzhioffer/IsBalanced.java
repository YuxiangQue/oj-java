package com.placeholder.jianzhioffer;

import com.placeholder.builtin.TreeNode;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class IsBalanced {
    public boolean IsBalanced_Solution(TreeNode root) {
        int[] height = new int[1];
        return isBalanced(root, height);
    }

    private boolean isBalanced(TreeNode root, int[] height) {
        if (root == null) {
            height[0] = 0;
            return true;
        }
        int[] left = new int[1];
        int[] right = new int[1];
        if (!isBalanced(root.left, left) || !isBalanced(root.right, right)) {
            return false;
        }
        height[0] = Math.max(right[0], left[0]) + 1;
        return Math.abs(left[0] - right[0]) <= 1;
    }
}
