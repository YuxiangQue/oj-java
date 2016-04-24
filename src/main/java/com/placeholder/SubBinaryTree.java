package com.placeholder;


import java.util.concurrent.locks.ReentrantLock;

public class SubBinaryTree {

    public static int isSubTree(TNode root1, TNode root2) {
        return innerIsSubTree(root1, root2) ? 1 : -1;
    }

    static boolean innerIsSubTree(TNode root1, TNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.value == root2.value) {
                result = sameTree(root1, root2);
            }
            if (!result) result = innerIsSubTree(root1.left, root2);
            if (!result) result = innerIsSubTree(root1.right, root2);
        }
        return result;
    }

    static boolean sameTree(TNode root1, TNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root2 == null)
            return false;
        if (root1 == null)
            return false;
        if (root1.value != root2.value)
            return false;
        return sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
    }

    public static void test1() {
        TNode root1 = new TNode();
        root1.value = 8;
        root1.right = new TNode();
        root1.right.value = 7;
        root1.left = new TNode();
        root1.left.value = 8;
        root1.left.left = new TNode();
        root1.left.left.value = 9;
        root1.left.right = new TNode();
        root1.left.right.value = 2;
        root1.left.right.left = new TNode();
        root1.left.right.left.left = new TNode();
        root1.left.right.left.left.value = 4;
        root1.left.right.left.right = new TNode();
        root1.left.right.left.right.value = 7;
        TNode root2 = new TNode();
        root2.value = 8;
        root2.left = new TNode();
        root2.left.value = 9;
        root2.right = new TNode();
        root2.right.value = 2;
        System.out.println(isSubTree(root1, root2));
        System.out.println(isSubTree(root2, root1));
        System.out.println(isSubTree(root1, root1.left));
        System.out.println(isSubTree(root1, null));
        System.out.println(isSubTree(null, root2));
        System.out.println(isSubTree(null, null));

    }

    public static void test2() {
        TNode root1 = new TNode();
        root1.value = 5;
        root1.right = new TNode();
        root1.right.value = 7;
        root1.left = new TNode();
        root1.left.value = 3;
        root1.left.left = new TNode();
        root1.left.left.value = 2;
        root1.left.right = new TNode();
        root1.left.right.value = 4;


        root1.right.left = new TNode();
        root1.right.left.value = 6;
        root1.right.right = new TNode();
        root1.right.right.value = 8;


        TNode root2 = new TNode();
        root2.value = 5;
        root2.left = new TNode();
        root2.left.value = 3;
        root2.right = new TNode();
        root2.right.value = 7;

        System.out.println(isSubTree(root1, root2) + "[False]");
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    static class TNode {
        TNode left;
        TNode right;
        int value;
    }
}
