package com.placeholder.jianzhioffer;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * @author yuxiangque
 * @version 2016/4/20
 */
public class GetNumberOfK {

    public static void main(String[] args) {
        System.out.println(GetNumberOfK(new int[]{3}, 3));
        System.out.println(GetNumberOfK(new int[]{2, 2, 2, 3}, 2));
        System.out.println(GetNumberOfK(new int[]{1, 2, 2, 3}, 2));
        System.out.println(GetNumberOfK(new int[]{1, 2, 2, 2}, 2));
    }

    public static int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0)
            return 0;
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] < k) {
                left = middle + 1;
            } else if (array[middle] > k) {
                right = middle - 1;
            } else {
                return seqSearch(array, middle, k);
            }
        }
        return 0;
    }

    private static int seqSearch(int[] array, int index, int k) {
        int left = index;
        int right = index;
        while (left >= 0 && array[left] == k) {
            --left;
        }
        while (right < array.length && array[right] == k) {
            ++right;
        }
        return right - left - 1;
    }
}
