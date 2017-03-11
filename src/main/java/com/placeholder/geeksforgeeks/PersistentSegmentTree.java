package com.placeholder.geeksforgeeks;

/**
 * http://www.geeksforgeeks.org/persistent-segment-tree-set-1-introduction/
 */
public class PersistentSegmentTree {
    static Node build(int[] arr, int low, int high) {
        if (low == high) {
            Node n = new Node(null, null, arr[low]);
            return n;
        }
        int mid = low + ((high - low) >> 1);
        Node n = new Node(null, null, 0);
        n.left = build(arr, low, mid);
        n.right = build(arr, mid + 1, high);
        n.value = n.left.value + n.right.value;
        return n;
    }

    static int query(Node n, int low, int high, int l, int h) {
        if (l > high || h < low || low > high) {
            return 0;
        }
        if (l <= low && high <= h) {
            return n.value;
        }
        int mid = (low) + ((high - low) >> 1);
        int p1 = query(n.left, low, mid, l, h);
        int p2 = query(n.right, mid + 1, high, l, h);
        return p1 + p2;
    }

    static Node update(Node prev, int low, int high, int index, int value) {
        if (index > high || index < low || low > high) {
            return null;
        }
        if (low == high) {
            Node cur = new Node(null, null, value);
            return cur;
        }
        int mid = low + ((high - low) >> 1);
        if (index <= mid) {
            Node cur = new Node(null, prev.right, 0);
            cur.left = update(prev.left, low, mid, index, value);
            cur.value = cur.left.value + cur.right.value;
            return cur;
        } else {
            Node cur = new Node(prev.left, null, 0);
            cur.right = update(prev.right, mid + 1, high, index, value);
            cur.value = cur.left.value + cur.right.value;
            return cur;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int n = nums.length;
        Node[] version = new Node[3];
        version[0] = build(nums, 0, n - 1);
        version[1] = update(version[0], 0, n - 1, 4, 1);
        version[2] = update(version[1], 0, n - 1, 0, 10);
        System.out.printf("Version 0, Query(0,4)=%d\n", query(version[0], 0, n - 1, 0, 4));
        System.out.printf("Version 1, Query(0,4)=%d\n", query(version[1], 0, n - 1, 0, 4));
        System.out.printf("Version 2, Query(0,4)=%d\n", query(version[2], 0, n - 1, 0, 4));
    }

    static class Node {
        Node left, right;
        int value;

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
