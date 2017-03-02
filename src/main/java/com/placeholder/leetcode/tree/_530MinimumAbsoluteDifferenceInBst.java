package com.placeholder.leetcode.tree;

import com.placeholder.predef.Codec;
import com.placeholder.predef.TreeNode;

public class _530MinimumAbsoluteDifferenceInBst {

    public static int helper(TreeNode root) {
        TreeNode pred = null, succ = null;
        TreeNode p = root.left;
        while (p != null) {
            pred = p;
            p = p.right;
        }
        p = root.right;
        while (p != null) {
            succ = p;
            p = p.left;
        }
        int diff = Integer.MAX_VALUE;
        if (pred != null && root.val - pred.val < diff) {
            diff = root.val - pred.val;
        }
        if (succ != null && succ.val - root.val < diff) {
            diff = succ.val - root.val;
        }
        return diff;
    }

    public static int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int minDiff = helper(root);
        int left = getMinimumDifference(root.left);
        if (left < minDiff) {
            minDiff = left;
        }
        int right = getMinimumDifference(root.right);
        if (right < minDiff) {
            minDiff = right;
        }
        return minDiff;
    }

    public static void main(String[] args) {
        TreeNode root = Codec.deserialize("236,104,n,227,n,n,701,n,911,n,n");
        System.out.println(getMinimumDifference(root));
    }
}
