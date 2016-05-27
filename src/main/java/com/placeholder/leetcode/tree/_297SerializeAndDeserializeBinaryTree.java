package com.placeholder.leetcode.tree;


import com.placeholder.predef.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _297SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(codec.serialize(root));
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<String> data = new ArrayList<>();
            levelOrderTraverse(root, treeNode -> {
                data.add(treeNode == null ? "null" : String.valueOf(treeNode.val));
            });
            for (int index = data.size() - 1; index >= 0; --index) {
                if (data.get(index).equals("null"))
                    data.remove(index);
                else
                    break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < data.size(); ++i) {
                String val = data.get(i);
                if (i == 0) {
                    sb.append(val);
                } else {
                    sb.append(",").append(val);
                }
            }
            sb.append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            data = data.substring(1, data.length() - 1);
            return null;
            // TODO
        }

        void levelOrderTraverse(TreeNode root, Visitor visitor) {
            if (null == root) {
                visitor.visit(null);
                return;
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

                visitor.visit(q);

                nodeQueue.poll();
                levelQueue.poll();
                if (q != null) {
                    nodeQueue.add(q.left);
                    levelQueue.add(level + 1);
                }
                if (q != null) {
                    nodeQueue.add(q.right);
                    levelQueue.add(level + 1);
                }
            }
        }


        @FunctionalInterface
        public interface Visitor {
            void visit(TreeNode treeNode);
        }
    }
}
