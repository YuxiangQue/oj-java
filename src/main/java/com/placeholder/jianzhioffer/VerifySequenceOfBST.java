package com.placeholder.jianzhioffer;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * @author yuxiangque
 * @version 2016/4/20
 */
public class VerifySequenceOfBST {
    public static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    // [left, right]
    private static boolean verify(int[] sequence, int left, int right) {
        if (left >= right) {
            return true;
        }

        // 找到右子树的起点
        int rightChildren = left;
        while (rightChildren < right && sequence[rightChildren] < sequence[right]) {
            ++rightChildren;
        }
        // 判断右子树是否都小于根节点
        for (int i = rightChildren; i < right; i++) {
            if (sequence[i] < sequence[right]) {
                return false;
            }
        }
        return verify(sequence, left, rightChildren - 1) && verify(sequence, rightChildren, right - 1);
    }

    public static void main(String[] args) {
        System.out.println(VerifySquenceOfBST(new int[]{4, 5, 3}));
        System.out.println(VerifySquenceOfBST(new int[]{4, 5}));
        System.out.println(VerifySquenceOfBST(new int[]{6, 5}));
        System.out.println(VerifySquenceOfBST(new int[]{4, 6, 7, 5}));
        System.out.println(VerifySquenceOfBST(new int[]{1, 3, 2, 7, 9, 8, 4}));
        System.out.println(VerifySquenceOfBST(new int[]{1, 2, 3, 4, 5}));
    }
}
