package com.placeholder.test.ntest2016spring1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * http://hihocoder.com/contest/ntest2016spring1/problem/1
 *
 * @author yuxiangque
 * @version 2016/4/10
 */
public class ElectronicDigit {

    private int[][] excludeSegmentCodeDigitsMap = new int[][]{
            {0},
            {1, 4}, // 1
            {1, 2, 3, 7},  // 2
            {5, 6}, // 3
            {1, 7, 0}, // 4
            {1, 3, 4, 5, 7, 9}, // 5
            {2}, // 6
            {1, 4, 7}, // 7
    };

    // 段码
    private Set<Integer> segmentCodeToCandidateDigits(int[] segmentCode) {
        Set<Integer> digits = new HashSet<>();
        for (int digit = 0; digit <= 9; digit++) {
            digits.add(digit);
        }
        for (int aSegmentCode : segmentCode) {
            int[] exclude = excludeSegmentCodeDigitsMap[aSegmentCode];
            for (int digit : exclude) {
                digits.remove(digit);
            }
        }
        return digits;
    }

    private int numLessThan(List<Set<Integer>> candidates, int n) {
        Set<Integer> nums = new HashSet<>();
        dfs(candidates, 0, 0, nums, n);
        return nums.size();
    }

    private void dfs(List<Set<Integer>> candidates, int index, int num, Set<Integer> nums, int n) {
        if (num > n) {
            return;
        }
        if (index == candidates.size()) {
            nums.add(num);
            return;
        }
        for (Integer candidate : candidates.get(index)) {
            dfs(candidates, index + 1, num * 10 + candidate, nums, n);
        }
    }

    public int electronicDigit(int[][] segmentCodes, int n) {
        List<Set<Integer>> num = new ArrayList<>();
        for (int[] segmentCode : segmentCodes) {
            Set<Integer> candidateDigits = segmentCodeToCandidateDigits(segmentCode);
            num.add(candidateDigits);
        }
        //
        return numLessThan(num, n);
    }


    @Test
    public void test() {
        Assert.assertEquals(3, electronicDigit(new int[][]{{3, 1}, {1, 4, 5}, {1, 5, 6, 7}}, 50));
        Assert.assertEquals(0, electronicDigit(new int[][]{{1, 2, 3}, {4, 5}, {6}, {7}}, 100));
        Assert.assertEquals(1, electronicDigit(new int[][]{{7}}, 1));

        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(32768, electronicDigit(new int[][]{
                    {1},
                    {1},
                    {1},
                    {1},
                    {1}}, (int) 10e9));
        }
    }

}
