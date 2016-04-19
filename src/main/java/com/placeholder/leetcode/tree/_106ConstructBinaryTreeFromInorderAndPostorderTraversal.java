package com.placeholder.leetcode.tree;

import com.placeholder.builtin.TreeNode;

import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    TreeNode inerBuildTree(List<Integer> inorder, int inorderBeginIndex, int inorderEndIndex,
                           List<Integer> postorder, int postorderBeginIndex, int postorderEndIndex) {
        if (postorderBeginIndex == postorderEndIndex)
            return null;
        int rootValue = postorder.get(postorderEndIndex - 1);
        int inorderRootIndex = 0;
        TreeNode root = new TreeNode(rootValue);
        for (int i = inorderBeginIndex; i < inorderEndIndex; ++i) {
            if (inorder.get(i) == rootValue) {
                inorderRootIndex = i;
                break;
            }
        }
        root.left = inerBuildTree(inorder, inorderBeginIndex, inorderRootIndex,
                postorder, postorderBeginIndex, postorderBeginIndex + inorderRootIndex - inorderBeginIndex);
        root.right = inerBuildTree(inorder, inorderRootIndex + 1, inorderEndIndex,
                postorder, postorderBeginIndex + inorderRootIndex - inorderBeginIndex, postorderEndIndex - 1);
        return root;
    }

    TreeNode buildTree(List<Integer> inorder, List<Integer> postorder) {
        int length = inorder.size();
        return inerBuildTree(inorder, 0, length, postorder, 0, length);
    }
}
