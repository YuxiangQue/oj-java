package com.placeholder.leetcode.tree;

import com.placeholder.predef.Codec;
import com.placeholder.predef.TreeNode;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class _543DiameterOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = Codec.deserialize("1,2,4,n,n,5,n,n,3");
        System.out.println(diameterOfBinaryTree(root));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] longest = new int[]{0};
        helper(root, longest);
        return longest[0] - 1;
    }

    private static int helper(TreeNode root, int[] longest) {
        if (root == null) return 0;
        int ld = helper(root.left, longest);
        int rd = helper(root.right, longest);
        longest[0] = Math.max(longest[0], 1 + ld + rd);
        return 1 + Math.max(ld, rd);
    }
}
