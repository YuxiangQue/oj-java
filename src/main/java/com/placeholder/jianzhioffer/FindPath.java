package com.placeholder.jianzhioffer;

import com.placeholder.predef.TreeNode;

import java.util.ArrayList;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class FindPath {
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        dfs(root, target, new ArrayList<>(), paths);
        return paths;
    }

    private static void dfs(TreeNode root, int target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {  // leaf
            target -= root.val;
            if (target == 0) {
                path.add(root.val);
                paths.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        if (root.left != null) {
            path.add(root.val);
            dfs(root.left, target - root.val, path, paths);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.val);
            dfs(root.right, target - root.val, path, paths);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        System.out.println(FindPath(root, 22));
    }
}
