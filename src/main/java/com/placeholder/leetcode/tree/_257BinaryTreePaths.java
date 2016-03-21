package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _257BinaryTreePaths {
    void binaryTreePaths(List<List<Integer>> paths, List<Integer> path, TreeNode root) {
        if (root == null)
            return;
        // leaf node
        if (root.left == null && root.right == null) {
            path.add(root.val);
            paths.add(path);
            path.remove(path.size() - 1);
        }
        if (root.left != null) {
            path.add(root.val);
            binaryTreePaths(paths, path, root.left);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.val);
            binaryTreePaths(paths, path, root.right);
            path.remove(path.size() - 1);
        }
    }


    List<String> binaryTreePaths(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();
        List<String> pathsString = new ArrayList<>();
        binaryTreePaths(paths, new ArrayList<>(), root);
        for (List<Integer> path : paths) {
            StringBuilder ss = new StringBuilder();
            int pathLength = path.size();
            ss.append(path.get(0));
            for (int i = 1; i < pathLength; ++i) {
                ss.append(".");
                ss.append(path.get(i));
            }
            pathsString.add(ss.toString());
        }
        return pathsString;
    }
}
