package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    TreeNode helper(List<Integer> preorder, int preorderBeginIndex, int preorderEndIndex,
                    List<Integer> inorder, int inorderBeginIndex, int inorderEndIndex) {
        if (preorderBeginIndex >= preorderEndIndex)
            return null;
        if (inorderBeginIndex >= inorderEndIndex)
            return null;
        int rootValue = preorder.get(preorderBeginIndex);
        int inorderRootIndex = 0;
        for (int i = inorderBeginIndex; i < inorderEndIndex; ++i) {
            if (inorder.get(i) == rootValue) {
                inorderRootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootValue);
        root.left = helper(preorder, preorderBeginIndex + 1,
                preorderBeginIndex + 1 + inorderRootIndex - inorderBeginIndex,
                inorder, inorderBeginIndex, inorderRootIndex);
        root.right = helper(preorder, preorderBeginIndex + 1 + inorderRootIndex - inorderBeginIndex,
                preorderEndIndex, inorder, inorderRootIndex + 1, inorderEndIndex);
        return root;
    }

    TreeNode buildTree(List<Integer> preorder, List<Integer> inorder) {
        int length = inorder.size();
        return helper(preorder, 0, length, inorder, 0, length);
    }
}
