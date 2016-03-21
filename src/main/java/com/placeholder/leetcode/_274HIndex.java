package com.placeholder.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/h-index/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _274HIndex {
    public static int hIndex(int[] citations) {
        int index = -1;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int tmp = citations[i] < i ? citations[i] : i;
            index = index > tmp ? index : tmp;
        }
        return index;
    }

    // countSort 变种
    // http://blog.csdn.net/xudli/article/details/48226129
    public static int hIndex1(int[] citations) {
        int length = citations.length;
        if (length == 0)
            return 0;
        int[] countArr = new int[length + 1];

        // 填充countArr
        for (int citation : citations) {
            if (citation >= length)
                countArr[length] += 1;
            else
                countArr[citation] += 1;
        }

        if (countArr[length] >= length)
            return length;

        for (int i = length - 1; i >= 0; --i) {
            countArr[i] = countArr[i] + countArr[i + 1];
            if (countArr[i] >= i)
                return i;
        }
        return 0;
    }

    // 0 0 0 1 1 3

    public static void main(String[] args) {
        System.out.println(hIndex1(new int[]{10, 8, 5, 4, 3}));
        System.out.println(hIndex1(new int[]{0, 0}));
    }
}
