package com.placeholder.geeksforgeeks;

import com.placeholder.predef.Codec;
import com.placeholder.predef.TreeNode;

import java.util.Stack;

public class StackTraverse {
    public static TreeNode inorderPredessor(TreeNode node) {
        TreeNode pred = node.left;
        while (pred.right != null && pred.right != node) {
            pred = pred.right;
        }
        return pred;
    }

    static void preorderTraverseMorris(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.println(current.val + ",");
                current = current.right;
            } else {

                // Find inorder predecessor
                TreeNode pred = inorderPredessor(current);

                if (pred.right == current) {
                    pred.right = null;
                    current = current.right;
                } else {
                    System.out.println(current.val + ",");
                    pred.right = current;
                    current = current.left;
                }
            }
        }
    }

    static void inorderTraverseMorris(TreeNode root) {
        if (root == null) return;
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.println(current.val + ",");
                current = current.right;
            } else {

                // Find inorder predecessor
                TreeNode pred = inorderPredessor(current);
                if (pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    System.out.println(current.val + ",");
                    pred.right = null;
                    current = current.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = Codec.deserialize("6,3,1,n,n,5,n,n,8,7,n,n,11,9,n,n,13,n,n");
        preorderTraverseMorris(root);
        // inorderTraverseMorris(root);
    }

    void preorderTraverseStack(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(node);
        while (!s.empty()) {
            node = s.pop();
            // visit
            if (node.right != null) {
                s.push(node.right);
            }
            if (node.left != null) {
                s.push(node.left);
            }
        }
    }

    void inorderTraverseStack(TreeNode node) {
        Stack<TreeNode> s = new Stack<>();
        while (!s.empty() || node != null) {
            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                node = s.pop();
                // visit
                node = node.right;
            }
        }
    }

    void postorderTraverseStack(TreeNode node) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode lastNodeVisited = null;
        while (!s.empty() || node != null) {
            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                TreeNode top = s.peek();
                if (top.right == null || lastNodeVisited == top.right) {
                    // visit
                    lastNodeVisited = top;
                    s.pop();
                } else {
                    node = top.right;
                }
            }
        }
    }
}
