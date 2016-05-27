package com.placeholder.leetcode.tree.levelorder;

import com.placeholder.predef.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _116PopulatingNextRightPintersInEachNode {
    void connect(TreeLinkNode root) {
        if (root == null)
            return;
        Queue<TreeLinkNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();

        TreeLinkNode prevNode = null;
        TreeLinkNode node = root;
        int prevLevel = -1;
        int level = 0;
        nodeQueue.offer(node);
        levelQueue.offer(level);
        while (!nodeQueue.isEmpty()) {
            prevLevel = level;
            prevNode = node;

            node = nodeQueue.peek();
            level = levelQueue.peek();

            if (prevNode == node)
                node.next = null;
            else if (level == prevLevel)
                prevNode.next = node;


            nodeQueue.poll();
            levelQueue.poll();
            if (node.left != null) {
                nodeQueue.offer(node.left);
                levelQueue.offer(level + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                levelQueue.offer(level + 1);
            }
        }
    }
}
