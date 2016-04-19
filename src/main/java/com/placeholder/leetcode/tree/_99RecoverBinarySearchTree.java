package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * #Tree #DepthFirstSearch
 * https://leetcode.com/problems/recover-binary-search-tree/
 * http://blog.csdn.net/linhuanmars/article/details/24566995
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _99RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(35);
        root.right = new TreeNode(30);
        root.right.right = new TreeNode(5);
        new _99RecoverBinarySearchTree().recoverTree(root);
    }

    public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        List<TreeNode> swap = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        prev[0] = null;
        recoverTree(root, prev, swap);
        if (swap.size() == 4) {
            int tmp = swap.get(0).val;
            swap.get(0).val = swap.get(3).val;
            swap.get(3).val = tmp;
        } else if (swap.size() == 2) {
            int tmp = swap.get(0).val;
            swap.get(0).val = swap.get(1).val;
            swap.get(1).val = tmp;
        }
    }

    // 中序遍历有序
    private void recoverTree(TreeNode root, TreeNode[] prev, List<TreeNode> swap) {
        if (root == null)
            return;
        recoverTree(root.left, prev, swap);
        System.out.println(root.val);
        if (prev[0] != null && prev[0].val > root.val) {
            swap.add(prev[0]);
            swap.add(root);
        }
        prev[0] = root;
        recoverTree(root.right, prev, swap);
    }
}
