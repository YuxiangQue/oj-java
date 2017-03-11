package com.placeholder.geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

public class ThreadedInorderTravserse {
    static void inorderTraverse(ThreadedTreeNode root, List<ThreadedTreeNode> queue) {
        if (root == null)
            return;
        inorderTraverse(root.left, queue);
        queue.add(root);
        inorderTraverse(root.right, queue);
    }

    static void threaded(ThreadedTreeNode root) {
        List<ThreadedTreeNode> queue = new ArrayList<>();
        inorderTraverse(root, queue);
        for (int i = 0; i < queue.size() - 1; ++i) {
            if (queue.get(i).right == null) {
                queue.get(i).threaded = true;
                queue.get(i).right = queue.get(i + 1);
            }
        }
    }

    static ThreadedTreeNode leftMost(ThreadedTreeNode root) {
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root;
    }

    static ThreadedTreeNode rightMost(ThreadedTreeNode root) {
        while (root != null && root.right != null) {
            root = root.right;
        }
        return root;
    }

    static void inorderTraverseThreaded(ThreadedTreeNode root) {
        ThreadedTreeNode cur = leftMost(root);
        while (cur != null) {
            System.out.println(cur.value + ",");
            if (cur.threaded) {
                cur = cur.right;
            } else {
                cur = leftMost(cur.right);
            }
        }
    }

    public static void main(String[] args) {
        ThreadedTreeNode root = new ThreadedTreeNode(6);
        root.left = new ThreadedTreeNode(3);
        root.right = new ThreadedTreeNode(8);
        root.left.left = new ThreadedTreeNode(1);
        root.left.right = new ThreadedTreeNode(5);
        root.right.left = new ThreadedTreeNode(7);
        root.right.right = new ThreadedTreeNode(11);
        root.right.right.left = new ThreadedTreeNode(9);
        root.right.right.right = new ThreadedTreeNode(13);
        threaded(root);
        inorderTraverseThreaded(root);
        return;
    }

    static class ThreadedTreeNode {
        ThreadedTreeNode left, right;
        int value;
        boolean threaded;

        public ThreadedTreeNode(int value) {
            this.value = value;
        }
    }

}
