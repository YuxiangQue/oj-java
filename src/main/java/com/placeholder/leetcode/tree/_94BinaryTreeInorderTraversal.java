package com.placeholder.leetcode.tree;

import com.placeholder.predef.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * #Tree
 * #Stack
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _94BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);

        System.out.println(new _94BinaryTreeInorderTraversal().inorderTraversal(root));
        System.out.println(new _94BinaryTreeInorderTraversal().inorderTraversal1(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.empty() || current != null) {
            if (current != null) { // 左中右
                stack.push(current);
                current = current.left;
            } else {
                TreeNode parent = stack.peek();
                list.add(parent.val);
                stack.pop();
                current = parent.right;
            }
        }
        return list;
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        TreeNode current = root;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || current != null) {
            // node代表当前准备处理的子树，层层向下把左孩子压栈，对应递归算法的左子树递归
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode parent = stack.pop(); // 弹出栈顶，对应递归算法的函数返回
            list.add(parent.val);
            current = parent.right; //将当前子树置为刚刚遍历过的结点的右孩子，对应递归算法的右子树递归
        }
        return list;
    }

}
