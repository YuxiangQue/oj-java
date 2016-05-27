package com.placeholder.leetcode.tree;

import com.placeholder.predef.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * #Tree
 * #Stack
 * #Design
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _173BinarySearchTreeIterator {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static class BSTIterator {

        Stack<TreeNode> stack;
        TreeNode currentNode;
        TreeNode parentNode;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            currentNode = root;
            parentNode = null;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.empty() || currentNode != null;
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            while (!stack.empty() || currentNode != null) {
                if (currentNode != null) {
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                } else {
                    parentNode = stack.pop();
                    currentNode = parentNode.right;
                    return parentNode.val;
                }
            }
            return 0;
        }
    }


}
