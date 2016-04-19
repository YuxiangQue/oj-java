package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _145BinaryTreePostorderTraversal {
    /**
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal1(TreeNode root) {
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
            current = parent.right; //将当前子树置为刚刚遍历过的结点的右孩子，对应递归算法的右子树递归

        }
        return list;
    }
}
