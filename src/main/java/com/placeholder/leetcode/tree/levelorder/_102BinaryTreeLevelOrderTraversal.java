package com.placeholder.leetcode.tree.levelorder;

import com.placeholder.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _102BinaryTreeLevelOrderTraversal {
    List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();

        List<List<Integer>> levels = new ArrayList<>();
        TreeNode q = root;
        int level = 0;
        nodeQueue.offer(q);
        levelQueue.offer(level);
        while (!nodeQueue.isEmpty()) {
            q = nodeQueue.peek();
            level = levelQueue.peek();
            if (levels.size() == level) {
                levels.add(new ArrayList<>());
            }
            levels.get(level).add(q.val);
            nodeQueue.poll();
            levelQueue.poll();
            if (q.left != null) {
                nodeQueue.add(q.left);
                levelQueue.add(level + 1);
            }
            if (q.right != null) {
                nodeQueue.add(q.right);
                levelQueue.add(level + 1);
            }
        }
        return levels;
    }
}
