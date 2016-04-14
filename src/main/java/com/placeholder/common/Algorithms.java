package com.placeholder.common;

/**
 * @author yuxiangque
 * @version 2016/3/22
 */
public class Algorithms {

    // 质数大于等于2 不能被它本身和1以外的数整除
    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i < (int) (Math.sqrt(n) + 1); ++i) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

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
            if (left >= right)  // arr[b] <= arr[pivot]
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
