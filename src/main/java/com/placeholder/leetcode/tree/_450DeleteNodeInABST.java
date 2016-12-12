package com.placeholder.leetcode.tree;

public class _450DeleteNodeInABST {
    public static TreeNode findMin(TreeNode root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    public static TreeNode deleteMin(TreeNode root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        return root;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            TreeNode temp = root;
            root = findMin(root.right);
            root.right = deleteMin(temp.right);
            root.left = temp.left;
        }
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
