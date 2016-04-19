package com.placeholder.leetcode.tree.levelorder;

import com.placeholder.builtin.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _103BinaryTreeZigzagLevelOrderTraversal {
    List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (null == root)
            return new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();

        List<List<Integer>> vec = new ArrayList<>();
        TreeNode q = root;
        int level = 0;
        nodeQueue.offer(q);
        levelQueue.offer(level);
        while (!nodeQueue.isEmpty()) {
            q = nodeQueue.peek();
            level = levelQueue.peek();
            if (vec.size() == level) {
                if (level > 0 && level % 2 == 0) {
                    //reverse(vec[level - 1].begin(), vec[level - 1].end());
                }
                vec.add(new ArrayList<>());
            }
            vec.get(level).add(q.val);
            nodeQueue.poll();
            levelQueue.poll();
            if (q.left != null) {
                nodeQueue.offer(q.left);
                levelQueue.offer(level + 1);

            }
            if (q.right != null) {
                nodeQueue.offer(q.right);
                levelQueue.offer(level + 1);
            }
        }
        if (level % 2 == 1) {
            //reverse(vec[level].begin(), vec[level].end());
        }
        return vec;
    }
}
