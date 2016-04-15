package com.placeholder.common.segment_tree;


import org.junit.Assert;
import org.junit.Test;

/**
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 *
 * @author yuxiangque
 * @version 2016/4/14
 */
public class SumSegmentTree {

    int[] sum;   // 保存所有的子节点的sum
    int[] arr;    // 数据

    public SumSegmentTree(int[] arr) {
        int n = arr.length;
        this.arr = arr;
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int maxSize = 2 * (int) Math.pow(2, height) - 1;
        sum = new int[maxSize];
        build(arr, 0, arr.length - 1, 0);
    }

    private static int middle(int left, int right) {
        return left + (right - left) / 2;
    }

    public void update(int index, int newValue) {
        if (index < 0 || index > arr.length - 1) {
            return;
        }
        update(0, arr.length - 1, index, newValue, 0);
    }

    public int sum(int rangeLeft, int rangeRight) {
        if (rangeLeft < 0 || rangeRight > arr.length - 1 || rangeLeft > rangeRight) {
            return 0;
        }
        return sum(0, arr.length - 1, rangeLeft, rangeRight, 0);
    }

    private int build(int arr[], int left, int right, int node) {
        if (left == right) {
            sum[node] = arr[left];
            return arr[left];
        }
        int middle = middle(left, right);
        sum[node] = build(arr, left, middle, node * 2 + 1) + build(arr, middle + 1, right, node * 2 + 2);
        return sum[node];
    }

    private int sum(int left, int right, int rangeLeft, int rangeRight, int node) {
        if (rangeLeft <= left && rangeRight >= right)
            return sum[node];
        if (right < rangeLeft || left > rangeRight)
            return 0;
        int tm = middle(left, right);
        return sum(left, tm, rangeLeft, rangeRight, 2 * node + 1) + sum(tm + 1, right, rangeLeft, rangeRight, 2 * node + 2);
    }

    private int update(int left, int right, int index, int newValue, int node) {
        if (left > right)
            return 0;
        if (index < left || index > right)
            return sum[node];
        if (index == left && index == right) {
            arr[index] = newValue;
            sum[node] = newValue;
            return sum[node];
        }
        int middle = middle(left, right);
        sum[node] = update(left, middle, index, newValue, 2 * node + 1) + update(middle + 1, right, index, newValue, 2 * node + 2);
        return sum[node];
    }

    public static class TestSegmentTree {

        @Test
        public void test() {
            int[] arr = {1, 3, 5, 7, 9, 11};
            SumSegmentTree tree = new SumSegmentTree(arr);
            Assert.assertEquals(15, tree.sum(1, 3));
            tree.update(1, 10);
            Assert.assertEquals(22, tree.sum(1, 3));

            arr = new int[]{2, 3, 5, 7, 9, 12, 15, 19, 20, 24};
            tree = new SumSegmentTree(arr);
            return;
        }
    }
}
