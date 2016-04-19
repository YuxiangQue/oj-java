package com.placeholder.leetcode.tree.levelorder;

import com.placeholder.builtin.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _111MinimumDepthOfBinaryTree {

    int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        Deque<ExtendedTreeNode> queue = new LinkedList<>();
        ExtendedTreeNode p = new ExtendedTreeNode(root, null, 1);
        queue.addLast(p);

        while (!queue.isEmpty()) {
            p = queue.peekFirst();
            TreeNode node = p.node;
            int depth = p.depth;
            queue.pollFirst();
            if (node.left != null) {
                queue.offerLast(new ExtendedTreeNode(node.left, node, depth + 1));
            }
            if (node.right != null) {
                queue.offerLast(new ExtendedTreeNode(node.right, node, depth + 1));
            }
            if (node.left == null && node.right == null) {
                return p.depth;
            }
        }
        return 0;
    }

    private static class ExtendedTreeNode {
        public TreeNode node;
        public TreeNode parent;
        public int depth;

        public ExtendedTreeNode(TreeNode node, TreeNode parent, int depth) {
            this.node = node;
            this.parent = parent;
            this.depth = depth;
        }
    }
}
