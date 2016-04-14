package com.placeholder.common;


import org.junit.Assert;
import org.junit.Test;

/**
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 *
 * @author yuxiangque
 * @version 2016/4/14
 */
public class SegmentTree {

    int[] tree;   // segment tree
    int[] arr;  // array of numbers

    public SegmentTree(int[] arr) {
        int n = arr.length;
        this.arr = arr;
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        tree = new int[maxSize];
        build(arr, 0, arr.length - 1, 0);
    }

    private static int middle(int left, int right) {
        return left + (right - left) / 2;
    }

    public void update(int index, int newValue) {
        if (index < 0 || index > arr.length - 1) {
            return;
        }
        int diff = newValue - arr[index];
        arr[index] = newValue;
        update(0, arr.length - 1, index, diff, 0);
    }

    public int sum(int i, int j) {
        if (i < 0 || j > arr.length - 1 || i > j) {
            return 0;
        }
        return sum(0, arr.length - 1, i, j, 0);
    }

    private int build(int a[], int tl, int tr, int v) {
        if (tl == tr) {
            tree[v] = a[tl];
            return a[tl];
        }
        int tm = middle(tl, tr);
        tree[v] = build(a, tl, tm, v * 2 + 1) + build(a, tm + 1, tr, v * 2 + 2);
        return tree[v];
    }

    private int sum(int tl, int tr, int l, int r, int v) {
        if (l <= tl && r >= tr)
            return tree[v];
        if (tr < l || tl > r)
            return 0;
        int tm = middle(tl, tr);
        return sum(tl, tm, l, r, 2 * v + 1) + sum(tm + 1, tr, l, r, 2 * v + 2);
    }

    private void update(int tl, int tr, int pos, int newValue, int v) {
        if (tl == tr) {
            tree[v] = newValue;
        } else {
            int tm = middle(tl, tr);
            if (pos < tm)
                update(tl, tm, pos, newValue, 2 * v + 1);
            else
                update(tm + 1, tr, pos, newValue, 2 * v + 2);
        }
    }

    public static class TestSegmentTree {

        @Test
        public void test() {
            int arr[] = {1, 3, 5, 7, 9, 11};
            SegmentTree tree = new SegmentTree(arr);
            Assert.assertEquals(15, tree.sum(1, 3));
            tree.update(1, 10);
            Assert.assertEquals(22, tree.sum(1, 3));
        }
    }
}
