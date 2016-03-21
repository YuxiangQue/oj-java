package com.placeholder.leetcode.tree;

import com.placeholder.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _95UniqueBinarySearchTree2 {

    public static void main(String[] args) {
        List<TreeNode> tree = new _95UniqueBinarySearchTree2().generateTrees(1);
        tree = new _95UniqueBinarySearchTree2().generateTrees(2);
        tree = new _95UniqueBinarySearchTree2().generateTrees(3);
    }

    // 0 n-1
    // 1 n-2
    // 2 n-3
    // ...
    // n-1 0
    // T(j) = T(0)*T(n-1) + ... + T(n-1)*T(0)
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0)
            return new ArrayList<>();
        return helper(1, n);
    }

    // https://leetcode.com/discuss/10254/a-simple-recursive-solution
    private List<TreeNode> helper(int begin, int end) {
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
            // 所有左组合
            List<TreeNode> left = helper(begin, i - 1);
            // 所有右组合
            List<TreeNode> right = helper(i + 1, end);
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
