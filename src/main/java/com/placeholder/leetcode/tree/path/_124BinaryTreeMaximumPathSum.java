package com.placeholder.leetcode.tree.path;

import com.placeholder.common.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _124BinaryTreeMaximumPathSum {
    public static int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        int[] maxPathSum = new int[1];
        maxPathSum[0] = Integer.MIN_VALUE;
        helper(root, maxPathSum);
        return maxPathSum[0];
    }

    private static int helper(TreeNode root, int[] maxPathSum) {
        if (root == null) {
            return 0;
        }
        int leftSum = helper(root.left, maxPathSum);
        int rightSum = helper(root.right, maxPathSum);
        leftSum = leftSum > 0 ? leftSum : 0;     // 小于0则忽略
        rightSum = rightSum > 0 ? rightSum : 0;  // 小于0则忽略
        int pathSum = leftSum + rightSum + root.val;
        if (pathSum > maxPathSum[0]) {
            maxPathSum[0] = pathSum;
        }
        boolean leftOrRight = leftSum > rightSum;
        return root.val + (leftOrRight ? leftSum : rightSum);  // 当前节点的路径和
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));

        root = new TreeNode(2);
        root.left = new TreeNode(-1);
        System.out.println(maxPathSum(root));

        // [9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6]
        root = new TreeNode(9);
        root.left = new TreeNode(6);
        root.right = new TreeNode(-3);
        root.right.left = new TreeNode(-6);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(2);
        root.right.right.left.left = new TreeNode(-6);
        root.right.right.left.right = new TreeNode(-6);
        root.right.right.left.left.left = new TreeNode(-6);
        System.out.println(maxPathSum(root));
    }
}
