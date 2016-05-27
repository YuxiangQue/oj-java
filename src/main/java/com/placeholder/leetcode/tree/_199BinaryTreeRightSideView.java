package com.placeholder.leetcode.tree;

import com.placeholder.predef.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _199BinaryTreeRightSideView {
    List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();
        if (root == null)
            return rightSideView;
        Queue<TreeNode> nodesQueue = new LinkedList<>();
        Queue<Integer> levelsQueue = new LinkedList<>();
        TreeNode prevNode = null;
        int prevLevel = -1;
        TreeNode node = root;
        int level = 0;
        nodesQueue.offer(node);
        levelsQueue.offer(level);
        while (!nodesQueue.isEmpty()) {
            prevLevel = level;
            prevNode = node;
            level = levelsQueue.poll();
            node = nodesQueue.poll();

            if (prevLevel != level)
                rightSideView.add(prevNode.val);
            if (node.left != null) {
                levelsQueue.offer(level + 1);
                nodesQueue.offer(node.left);
            }
            if (node.right != null) {
                levelsQueue.offer(level + 1);
                nodesQueue.offer(node.right);
            }
            if (nodesQueue.isEmpty()) {
                rightSideView.add(node.val);
            }
        }
        return rightSideView;
    }
}
