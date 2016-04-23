package com.placeholder.test.msbop2016;

import java.util.Arrays;


public class _01 {

    private static void _quickSort(FloatWrapper[] arr, int begin, int end) {
        if (begin >= end)
            return;
        FloatWrapper pivotValue = arr[begin];
        int left = begin + 1;
        int right = end;
        for (; ; ) {
            while (left <= end && arr[left].value - pivotValue.value < 0) {
                ++left;
            }
            while (right > begin && arr[right].value - pivotValue.value >= 0) {
                --right;
            }
            if (left >= right)
                break;
            FloatWrapper tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            ++left;
            --right;
        }

        // 因为pivot是首元素，即表示最后要跟一个比pivot小的元素还位置，应该是right
        FloatWrapper tmp1 = arr[begin];
        arr[begin] = arr[right];
        arr[right] = tmp1;
        _quickSort(arr, begin, right - 1);
        _quickSort(arr, right + 1, end);
    }

    public static void quickSort(FloatWrapper[] arr) {
        int len = arr.length;
        _quickSort(arr, 0, len - 1);
    }

    public static int[] Puzzle(float[] a) {
        int[] result = new int[a.length];
        FloatWrapper[] arr = new FloatWrapper[a.length];
        for (int index = 0; index < a.length; index++) {
            arr[index] = new FloatWrapper(a[index], index);
        }
        quickSort(arr);
        int counter = 0;
        while (counter < arr.length) {
            int local = 1;
            while (counter + local < arr.length && arr[counter].value == arr[counter + local].value) {
                ++local;
            }
            for (int i = 0; i < local; i++) {
                result[arr[counter + i].index] = counter;
            }
            counter += local;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Puzzle(new float[]{0, 0, 0, 0})));
        System.out.println(Arrays.toString(Puzzle(new float[]{7.5398f, 6.5398f, 1, 1})));
        System.out.println(Arrays.toString(Puzzle(new float[]{0, 0, 0})));
        System.out.println(Arrays.toString(Puzzle(new float[]{12.5f, -7.3f, 5.3f})));
        System.out.println(Arrays.toString(Puzzle(new float[]{-6.5f, -8.9f, 10.3f, -8.9f})));
    }

    static class FloatWrapper {
        float value;
        int index;

        FloatWrapper(float value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
