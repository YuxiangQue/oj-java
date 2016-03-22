package com.placeholder.jianzhioffer;

import com.placeholder.common.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class ReConstructBinaryTree {
    TreeNode reConstructBinaryTree(List<Integer> preOrder,
                                   int preOrderLeft,
                                   int preOrderRight,
                                   Map<Integer, Integer> inOrder,
                                   int inOrderLeft,
                                   int inOrderRight) {
        if (preOrderLeft > preOrderRight)
            return null;
        if (inOrderLeft > inOrderRight)
            return null;
        int rootValue = preOrder.get(preOrderLeft);
        int rootInorderIndex = inOrder.get(rootValue);
        int leftSubtreeCount = rootInorderIndex - inOrderLeft;
        TreeNode root = new TreeNode(rootValue);
        root.left = reConstructBinaryTree(preOrder,
                preOrderLeft + 1,
                preOrderLeft + leftSubtreeCount,
                inOrder,
                inOrderLeft,
                inOrderLeft + leftSubtreeCount);
        root.right = reConstructBinaryTree(preOrder,
                preOrderLeft + 1 + leftSubtreeCount,
                preOrderRight,
                inOrder,
                inOrderLeft + leftSubtreeCount + 1,
                inOrderRight);
        return root;
    }

    TreeNode reConstructBinaryTree(List<Integer> pre, List<Integer> in) {
        Map<Integer, Integer> inOrder = new HashMap<>();
        int length = in.size();
        for (int i = 0; i < length; ++i) {
            inOrder.put(in.get(i), i);
        }
        return reConstructBinaryTree(pre, 0, length - 1, inOrder, 0, length - 1);
    }
}
