package com.placeholder.geeksforgeeks;

public class LazyRmq {
    public static Node build(int[] nums, int lower, int upper) {
        if (lower == upper) {
            return new Node(nums[lower]);
        }
        int mid = lower + (upper - lower) / 2;
        Node node = new Node(0);
        node.left = build(nums, lower, mid);
        node.right = build(nums, mid + 1, upper);
        node.value = node.left.value + node.right.value;
        return node;
    }

    private static void lazy(Node root, int lower, int upper) {
        if (root.lazy != 0) {
            root.value += (upper - lower + 1) * root.lazy;
            if (lower != upper) {
                root.left.lazy += root.lazy;
                root.right.lazy += root.lazy;
            }
            root.lazy = 0;
        }
    }

    public static int query(Node root, int lower, int upper, int l, int u) {
        lazy(root, lower, upper);

        if (l > upper || u < lower || lower > upper) {
            return 0;
        }
        if (l <= lower && u >= upper) {
            return root.value;
        }
        int mid = lower + (upper - lower) / 2;
        int left = query(root.left, lower, mid, l, u);
        int right = query(root.right, mid + 1, upper, l, u);
        return left + right;
    }

    public static void updateRange(Node root, int lower, int upper, int l, int u, int diff) {
        lazy(root, lower, upper);

        if (l > upper || u < lower || lower > upper) {
            return;
        }

        if (l <= lower && upper <= u) {
            root.value += (upper - lower + 1) * diff;
            if (lower != upper) {
                root.left.lazy += diff;
                root.right.lazy += diff;
            }
            return;
        }

        int mid = lower + (upper - lower) / 2;
        updateRange(root.left, lower, mid, l, u, diff);
        updateRange(root.right, mid + 1, upper, l, u, diff);
        root.value = root.left.value + root.right.value;
    }


    public static void update(Node root, int lower, int upper, int index, int value) {
        if (index > upper || index < lower || lower > upper) {
            return;
        }
        if (lower == upper) {
            root.value = value;
            return;
        }
        int mid = lower + (upper - lower) / 2;
        update(root.right, mid + 1, upper, index, value);
        update(root.left, lower, mid, index, value);
        root.value = root.left.value + root.right.value;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 5, 6, 7, 8, 2, 23, 4, 1, 4};
        int n = nums.length - 1;
        Node root = build(nums, 0, n - 1);
        System.out.println(query(root, 0, n - 1, 0, 3));
        System.out.println(query(root, 0, n - 1, 1, 2));
        updateRange(root, 0, n - 1, 0, 2, 1);
        System.out.println(query(root, 0, n - 1, 1, 2));
    }

    static class Node {
        Node left;
        Node right;
        int value;
        int lazy;

        public Node(int value) {
            this.value = value;
        }
    }
}
