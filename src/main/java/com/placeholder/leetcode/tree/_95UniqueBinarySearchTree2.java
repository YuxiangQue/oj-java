package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 * https://leetcode.com/discuss/10254/a-simple-recursive-solution
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _95UniqueBinarySearchTree2 {

    public static void main(String[] args) {
        List<TreeNode> tree = generateTrees(3);
        //  tree = generateTrees(4);
        //  tree = generateTrees(5);
    }

    /**
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0)
            return new ArrayList<>();
        return helper(1, n);
    }

    private static List<TreeNode> helper(int begin, int end) {
        List<TreeNode> tree = new ArrayList<>();
        if (begin > end) {
            // 空也算
            tree.add(null);
            return tree;
        }
        if (begin == end) {
            tree.add(new TreeNode(begin));
            return tree;
        }
        for (int i = begin; i <= end; ++i) {
            List<TreeNode> left = helper(begin, i - 1); // 所有左组合
            List<TreeNode> right = helper(i + 1, end); // 所有右组合
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    tree.add(root);
                }
            }
        }
        return tree;
    }
}
