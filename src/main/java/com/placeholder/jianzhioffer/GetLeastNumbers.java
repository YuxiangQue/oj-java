package com.placeholder.jianzhioffer;

import java.util.ArrayList;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class GetLeastNumbers {

    public static int partition(int[] arr, int lower, int upper, int k) {
        if (lower >= upper) {
            return lower;
        }
        int left = lower + 1;
        int right = upper;
        while (true) {
            while (right <= upper && arr[left] < arr[lower]) {
                ++left;
            }
            while (right >= lower && arr[right] > arr[lower]) {
                --right;
            }
            if (left >= right) {
                break;
            }
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }

        int tmp = arr[right];
        arr[right] = arr[lower];
        arr[lower] = tmp;

        if (right + 1 == k) {
            return right;
        } else if (right + 1 > k) {
            return partition(arr, lower, right, k);
        } else {
            return partition(arr, right + 1, upper, k);
        }
    }

    private static void quickSort(int[] arr, int lower, int upper) {
        _quickSort(arr, 0, arr.length - 1);
    }

    private static void _quickSort(int[] arr, int lower, int upper) {
        if (lower >= upper) {
            return;
        }
        int left = lower + 1;
        int right = upper;
        while (true) {
            while (right <= upper && arr[left] < arr[lower]) {
                ++left;
            }
            while (right >= lower && arr[right] > arr[lower]) {
                --right;
            }
            if (left >= right) {
                break;
            }
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
        }
        int tmp = arr[right];
        arr[right] = arr[lower];
        arr[lower] = tmp;
        _quickSort(arr, lower, right - 1);
        _quickSort(arr, right + 1, upper);
    }


    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (input == null || input.length == 0 || k <= 0) {
            return result;
        }
        if (k < input.length) {
            partition(input, 0, input.length - 1, k);
        } else { // k >= input.length
            k = input.length;
        }
        quickSort(input, 0, k);
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 1));
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 1));
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 2));
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 3));
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4));
        System.out.println(GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 14));
    }
}
