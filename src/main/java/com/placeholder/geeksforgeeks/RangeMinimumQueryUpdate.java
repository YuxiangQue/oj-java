package com.placeholder.geeksforgeeks;

public class RangeMinimumQueryUpdate {
    static Node build(int[] nums, int lower, int upper) {
        if (lower == upper) {
            return new Node(nums[lower]);
        }
        int mid = lower + (upper - lower) / 2;
        Node node = new Node(0);
        node.left = build(nums, lower, mid);
        node.right = build(nums, mid + 1, upper);
        node.value = Math.max(node.left.value, node.right.value);
        return node;
    }

    static int query(Node root, int lower, int upper, int l, int u) {
        if (l > upper || u < lower || lower > upper) {
            return 0;
        }
        if (l <= lower && u >= upper) {
            return root.value;
        }
        int mid = lower + (upper - lower) / 2;
        int left = query(root.left, lower, mid, l, u);
        int right = query(root.right, mid + 1, upper, l, u);
        return Math.max(left, right);
    }

    static Node update(Node prev, int lower, int upper, int index, int value) {
        if (index > upper || index < lower || lower > upper) {
            return prev;
        }
        if (lower == upper) {
            return new Node(value);
        }
        int mid = lower + (upper - lower) / 2;
        Node node = new Node(0);
        if (index <= mid) {
            node.right = prev.right;
            node.left = update(prev.left, lower, mid, index, value);
        } else {
            node.left = prev.left;
            node.right = update(prev.right, mid + 1, upper, index, value);
        }
        node.value = Math.max(node.left.value, node.right.value);
        return node;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 5, 6, 7, 8, 2, 23, 4, 1, 4};
        int n = nums.length - 1;
        Node root = build(nums, 0, n - 1);
        System.out.println(query(root, 0, n - 1, 0, n - 1));
        System.out.println(query(root, 0, n - 1, 0, 4));
        System.out.println(query(root, 0, n - 1, 5, 6));
        root = update(root, 0, n - 1, 0, 24);
        System.out.println(query(root, 0, n - 1, 0, n - 1));
    }

    static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
