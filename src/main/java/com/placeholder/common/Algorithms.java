package com.placeholder.common;

/**
 * @author yuxiangque
 * @version 2016/3/22
 */
public class Algorithms {
    public static int bsearch(int[] arr, int target) {
        int n = arr.length;
        int upper = n - 1;
        int lower = 0;
        while (lower <= upper) {
            int middle = lower + (upper - lower) / 2;
            if (target < arr[middle]) {
                upper = middle - 1;
            } else if (target > arr[middle]) {
                lower = middle + 1;
            } else {
                return middle;
            }
        }
        return lower;
    }
}
