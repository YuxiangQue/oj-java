package com.placeholder.common;

/**
 * @author yuxiangque
 * @version 2016/3/22
 */
public class Algorithms {
    public static int partition(int[] arr, int lower, int upper) {
        int pivot = lower;
        int left = lower + 1;
        int right = upper;

        while (true) {
            while (left < upper && arr[left] < arr[pivot]) {
                ++left;
            }
            while (right >= lower && arr[right] > arr[pivot]) {
                --right;
            }
            if (left >= right)  // arr[right] <= arr[pivot]
                break;
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            ++left;
            --right;
        }

        int temp = arr[right];
        arr[right] = arr[pivot];
        arr[pivot] = temp;
        return right;
    }
}
