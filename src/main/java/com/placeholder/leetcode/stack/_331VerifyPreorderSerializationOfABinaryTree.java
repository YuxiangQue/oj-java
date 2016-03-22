package com.placeholder.leetcode.stack;

import java.util.*;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 * https://www.hrwhisper.me/leetcode-verify-preorder-serialization-of-a-binary-tree/
 *
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _331VerifyPreorderSerializationOfABinaryTree {

    /**
     * @param preorder
     * @return
     */
    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        List<String> stack = new LinkedList<>();
        int stackIndex = 0;
        for (String node : nodes) {
            stack.add(stackIndex++, node);
            while (stack.size() >= 3 &&
                    Objects.equals(stack.get(stackIndex - 1), "#") &&
                    Objects.equals(stack.get(stackIndex - 2), "#") &&
                    !Objects.equals(stack.get(stackIndex - 3), "#")) {
                stack.remove(--stackIndex);
                stack.remove(--stackIndex);
                stack.remove(--stackIndex);
                stack.add(stackIndex++, "#");
            }
        }
        return stackIndex == 1 && Objects.equals(stack.get(0), "#");
    }

    public static void main(String[] args) {
        isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }
}
