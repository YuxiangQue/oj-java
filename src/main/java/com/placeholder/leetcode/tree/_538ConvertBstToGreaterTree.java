package com.placeholder.leetcode.tree;

import com.placeholder.predef.Codec;
import com.placeholder.predef.TreeNode;

public class _538ConvertBstToGreaterTree {

    public static void main(String[] args) {
        TreeNode root = Codec.deserialize("5,2,n,n,13,n,n");
        root = convertBST(root);
        return;
    }

    public static TreeNode convertBST(TreeNode root) {
        int[] sum = new int[]{0};
        inorderTraverse(root, sum);
        return root;
    }

    public static void inorderTraverse(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.right, sum);
        root.val += sum[0];
        sum[0] = root.val;
        inorderTraverse(root.left, sum);
    }
}
