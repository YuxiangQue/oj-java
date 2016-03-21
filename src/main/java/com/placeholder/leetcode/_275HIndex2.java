package com.placeholder.leetcode;

import java.util.Arrays;

/**
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _275HIndex2 {

    public static void ascend(int[] citations) {
        Arrays.sort(citations);
    }

    public static int hIndex(int[] citations) {
        ascend(citations);
        int length = citations.length;
        int left = 0;
        int right = length - 1;


        while (left <= right) {
            int mid = (left + right) / 2;
            int val = citations[mid];
            int behind = length - mid;
            if (val >= behind) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return length - right - 1;
    }

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{10, 8, 5, 4, 3}));
        System.out.println(hIndex(new int[]{0, 0}));
    }
}
