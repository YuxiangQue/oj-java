package com.placeholder.jianzhioffer;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class MinNumberInRotatedArray {
    public int minNumberInRotateArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {

            int middle = left + (right - left) / 2;

            // 升序
            if (arr[left] <= arr[right]) {
                return arr[left];
            }

            if (arr[middle] > arr[right]) {
                right = middle + 1;
            } else if (arr[middle] < arr[right]) {
                left = middle;
            } else {
                left = middle + 1;
            }
        }
        return arr[left];
    }
}
