package com.placeholder.leetcode.segment_tree;

import com.placeholder.common.segment_tree.SumSegmentTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/range-sum-query-mutable/
 * #CoverSegmentTree
 * #BinaryIndexedTree
 *
 * @author yuxiangque
 * @version 2016/4/14
 */
public class _307RangeSumQueryMutable {

    @Test
    public void test() {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        Assert.assertEquals(9, numArray.sumRange(0, 2));
        numArray.update(1, 2);
        Assert.assertEquals(8, numArray.sumRange(0, 2));

        numArray = new NumArray(new int[]{});
        Assert.assertEquals(0, numArray.sumRange(0, 0));

        numArray = new NumArray(new int[]{1});
        Assert.assertEquals(1, numArray.sumRange(0, 0));
    }

    public class NumArray {

        SumSegmentTree sumSegmentTree;

        public NumArray(int[] nums) {
            if (nums == null || nums.length == 0)
                return;
            sumSegmentTree = new SumSegmentTree(nums);
        }

        void update(int i, int val) {
            if (sumSegmentTree == null)
                return;
            sumSegmentTree.update(i, val);
        }

        // [i,j]
        public int sumRange(int i, int j) {
            if (sumSegmentTree == null)
                return 0;
            return sumSegmentTree.sum(i, j);
        }
    }
}
