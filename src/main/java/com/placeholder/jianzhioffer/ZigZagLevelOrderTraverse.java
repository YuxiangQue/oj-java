package com.placeholder.jianzhioffer;

import com.placeholder.predef.TreeNode;

import java.util.ArrayList;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class ZigZagLevelOrderTraverse {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null)
            return result;

        ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
        ArrayList<Integer> levels = new ArrayList<Integer>();
        nodes.add(pRoot);
        levels.add(0);
        int currentLevel = -1;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.remove(0);
            int level = levels.remove(0);
            if (currentLevel != level) {
                result.add(new ArrayList<Integer>());
                currentLevel = level;
            }
            // the last level
            ArrayList<Integer> temp = result.get(result.size() - 1);
            if (level % 2 == 0) {
                temp.add(node.val);
            } else {
                temp.add(0, node.val);
            }

            if (node.left != null) {
                nodes.add(node.left);
                levels.add(level + 1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                levels.add(level + 1);
            }
        }
        return result;
    }
}
