package com.placeholder.jianzhioffer;

import com.placeholder.builtin.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class PrintFromTopToBottom {
    public static void main(String[] args) {

    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode next = queue.poll();
            result.add(next.val);
            if (next.left != null) {
                queue.offer(next.left);
            }
            if (next.right != null) {
                queue.offer(next.right);
            }
        }
        return result;
    }
}
