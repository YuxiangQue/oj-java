package com.placeholder.common;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/4/14
 */
public class SegmentTree {

    int[] st;   // segment tree
    int[] arr;  // array of numbers

    public SegmentTree(int arr[]) {
        int n = arr.length;
        this.arr = arr;
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        st = new int[maxSize];
        buildSegmentTree(arr, 0, arr.length - 1, 0);
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

    public int sumRange(int i, int j) {
        // 0 <= i <= j < arr.length
        if (i < 0 || j > arr.length - 1 || i > j) {
            return 0;
        }
        return sumRange(0, arr.length - 1, i, j, 0);
    }

    private int buildSegmentTree(int arr[], int left, int right, int root) {
        if (left == right) {
            st[root] = arr[left];
            return arr[left];
        }
        int middle = middle(left, right);
        st[root] = buildSegmentTree(arr, left, middle, root * 2 + 1) +
                buildSegmentTree(arr, middle + 1, right, root * 2 + 2);
        return st[root];
    }

    private int sumRange(int left, int right, int i, int j, int root) {
        if (i <= left && j >= right)
            return st[root];

        if (right < i || left > j)
            return 0;

        int middle = middle(left, right);
        return sumRange(left, middle, i, j, 2 * root + 1) +
                sumRange(middle + 1, right, i, j, 2 * root + 2);
    }

    private void update(int left, int right, int index, int diff, int root) {
        if (index < left || index > right)
            return;

        st[root] = st[root] + diff;
        if (right != left) {
            int mid = middle(left, right);
            update(left, mid, index, diff, 2 * root + 1);
            update(mid + 1, right, index, diff, 2 * root + 2);
        }
    }

    public static class TestSegmentTree {

        @Test
        public void test() {
            int arr[] = {1, 3, 5, 7, 9, 11};
            SegmentTree tree = new SegmentTree(arr);
            Assert.assertEquals(15, tree.sumRange(1, 3));
            tree.update(1, 10);
            Assert.assertEquals(22, tree.sumRange(1, 3));
        }
    }
}
