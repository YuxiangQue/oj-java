package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * #Tree
 * #Stack
 *
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _144BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);

        System.out.println(new _144BinaryTreePreorderTraversal().preorderTraversal(root));
        System.out.println(new _144BinaryTreePreorderTraversal().preorderTraversal1(root));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode currentNode = root;
        while (!stack.empty() || currentNode != null) {
            if (currentNode != null) {  // 中左右
                list.add(currentNode.val);
                stack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                TreeNode parentNode = stack.pop();
                currentNode = parentNode.right;
            }
        }
        return list;
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        TreeNode current = root;
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || current != null) {
            // node代表当前准备处理的子树，层层向下把左孩子压栈，对应递归算法的左子树递归
            while (current != null) {
                list.add(current.val);
                stack.push(current);
                current = current.left;
            }
            TreeNode parent = stack.pop(); // 弹出栈顶，对应递归算法的函数返回
            current = parent.right; //将当前子树置为刚刚遍历过的结点的右孩子，对应递归算法的右子树递归
        }
        return list;
    }
}
