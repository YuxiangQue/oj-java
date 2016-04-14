package com.placeholder.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 阙宇翔
 * @version 2016/2/25
 */
public class Sorts {

    public static <T extends Comparable<T>> void mergeSort(T[] arr) {

    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        int len = arr.length;
        int i, j;
        for (i = 0; i < len - 1; ++i) {
            boolean needNextPass = true;
            for (j = len - 1; j > i; --j) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                    needNextPass = false;
                }
            }
            if (needNextPass)
                break;
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        int len = arr.length;
        int i, j, low;
        for (i = 0; i < len - 1; i++) {
            low = i;
            for (j = i + 1; j < len; j++)
                if (arr[j].compareTo(arr[low]) < 0)
                    low = j;
            T temp = arr[i];
            arr[i] = arr[low];
            arr[low] = temp;
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T arr[]) {
        int len = arr.length;
        int i, j;
        for (i = 1; i < len; ++i) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                T temp = arr[i];
                for (j = i - 1; arr[j].compareTo(temp) > 0 && j >= 0; --j)
                    arr[j + 1] = arr[j];
                arr[j + 1] = temp;
            }
        }
    }

    public static <T extends Comparable<T>> void shellSort(T arr[]) {
        int len = arr.length;
        int h;
        for (h = 1; h < len; h = 3 * h + 1) {
        }

        for (; h > 0; h /= 3) {
            for (int i = h; i < len; ++i) {
                T temp = arr[i];
                int j = i - h;
                for (; j >= 0 && arr[j].compareTo(temp) > 0; j -= h)
                    arr[j + h] = arr[j];
                arr[j + h] = temp;
            }
        }
    }

    public static <T extends Comparable<T>> int partition(T arr[], int begin, int end) {
        if (begin >= end)
            return begin;
        T pivotValue = arr[begin];
        int left = begin + 1;
        int right = end;
        for (; ; ) {
            for (; left <= end && arr[left].compareTo(pivotValue) < 0; ++left) {
            }

            //循环结束时保证left指向小于等于pivot的数，right指向大于pivot的数
            for (; right > begin && arr[right].compareTo(pivotValue) >= 0; --right) {
            }
            if (left >= right)
                break;
            T tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            ++left;
            --right;
        }

        // 因为pivot是首元素，即表示最后要跟一个比pivot小的元素还位置，应该是right
        T tmp = arr[begin];
        arr[begin] = arr[right];
        arr[right] = tmp;
        return right;
    }

    private static <T extends Comparable<T>> void _quickSort(T arr[], int begin, int end) {
        if (begin >= end)
            return;
        T pivotValue = arr[begin];
        int left = begin + 1;
        int right = end;
        for (; ; ) {
            for (; left <= end && arr[left].compareTo(pivotValue) < 0; ++left) {
            }

            // 循环结束时保证left指向小于等于pivot的数，right指向大于pivot的数
            for (; right > begin && arr[right].compareTo(pivotValue) >= 0; --right) {
            }
            if (left >= right)
                break;
            T tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            ++left;
            --right;
        }

        // 因为pivot是首元素，即表示最后要跟一个比pivot小的元素还位置，应该是right
        T tmp = arr[begin];
        arr[begin] = arr[right];
        arr[right] = tmp;
        _quickSort(arr, begin, right - 1);
        _quickSort(arr, right + 1, end);
    }

    public static <T extends Comparable<T>> void quickSort(T arr[]) {
        int len = arr.length;
        _quickSort(arr, 0, len - 1);
    }

    private static <T extends Comparable<T>> int _partition(T arr[], int left, int right) {
        T pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right - 1; ++j) {
            if (arr[j].compareTo(pivot) <= 0) {
                ++i;
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return right;
    }

    private static <T extends Comparable<T>> void _quickSort1(T arr[], int left, int right) {
        if (left < right) {
            int pivotIndex = _partition(arr, left, right);
            _quickSort1(arr, left, pivotIndex - 1);
            _quickSort1(arr, pivotIndex + 1, right);
        }
    }

    public static <T extends Comparable<T>> void quickSort1(T arr[]) {
        int len = arr.length;
        _quickSort1(arr, 0, len - 1);
    }

    /**
     * 堆调整
     *
     * @param arr      待调整的数组
     * @param len
     * @param toAdjust 待调整的数组元素的位
     * @param <T>
     */
    public static <T extends Comparable<T>> void siftUp(T[] arr, int len, int toAdjust) {
        int child;
        T tmp;
        for (tmp = arr[toAdjust]; 2 * toAdjust + 1 < len; toAdjust = child) {
            child = 2 * toAdjust + 1; // 子结点的位置是父结点位置 * 2 + 1
            if (child != len - 1
                    && arr[child + 1].compareTo(arr[child]) > 0) // 得到子结点中较大的结点
                ++child;
            if (tmp.compareTo(arr[child]) < 0)//如果较大的子结点大于父结点那么把较大的子结点上移替换其父结点
                arr[toAdjust] = arr[child];
            else
                break;
        }
        arr[toAdjust] = tmp;// 最后把需要调整的元素值放到合适的位置
    }

    /**
     * 堆排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int len = arr.length;
        int i;
        for (i = len / 2 - 1; i >= 0; --i)
            siftUp(arr, len, i);
        for (i = len - 1; i > 0; --i) {
            T tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            siftUp(arr, i, 0);
        }
    }

    // 鸽巢排序
    public static void pigeonholeSort(Integer arr[]) {
        int len = arr.length;
        int i, j;
        int index = 0;
        int half = len / 2;
        Integer max;
        Integer max1 = arr[0];
        Integer max2 = arr[half];
        for (i = 1; i < half; ++i) {
            max1 = arr[i].compareTo(max1) > 0 ? arr[i] : max1;
        }
        for (i = half + 1; i < len; ++i) {
            max2 = (arr[i].compareTo(max2) > 0) ? arr[i] : max2;
        }
        max = (max1.compareTo(max2) > 0) ? max1 : max2;
        ++max;
        Integer[] tmp = new Integer[max];
        for (i = 0; i < len; ++i)
            ++tmp[arr[i]];
        for (i = 0; i <= max; ++i) {
            for (j = 0; j < tmp[i]; ++j) {
                arr[index++] = i;
            }
        }
    }

    /**
     * 测试排序性能
     *
     * @param sortFunction 待排序函数
     * @param size         数据规模
     * @return
     */
    public static double benchmark(SortFunction<Integer> sortFunction, int size, int maxTimes) {

        System.out.println("数组准备...");
        System.out.println("数据规模: " + size);
        System.out.printf("----------------------------------------------------");
        Integer[] arr1 = new Integer[size];
        Integer[] arr2 = new Integer[size];
        Random random = new Random();

        //准备数据
        for (int i = 0; i < size; ++i) {
            arr1[i] = arr2[i] = (random.nextInt() << 4) + random.nextInt() + (i >> 2);
        }

        //准备多轮测试
        long[] testTimesMS = new long[maxTimes];

        int testCount = 0;

        for (testCount = 1; ; ++testCount) {
            System.out.println("开始第" + testCount + "次排序...");


            Long start = System.currentTimeMillis();
            sortFunction.sort(arr1);
            Long end = System.currentTimeMillis();
            testTimesMS[testCount - 1] = end - start;

            boolean ok = true;
            for (int i = 0; i < size - 1; ++i) {

                // 排序失败
                if (arr1[i] > arr1[i + 1]) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                System.out.println("排序成功!");
            } else {
                System.out.println("排序错误!");
            }
            System.out.println("耗时" + testTimesMS[testCount - 1] + "毫秒");

            if (!ok) {
                --testCount;
                break;
            }

            if (testCount >= maxTimes)
                break;

            // 下一轮排序数据准备
            arr1 = Arrays.copyOf(arr2, size);
        }

        // 计算平均成绩
        System.out.println("----------------------------------------------------");
        System.out.println("测试次数：" + testCount + "次");

        long sumMS = 0;
        for (int i = 0; i < testCount; i++) {
            sumMS += testTimesMS[i];
        }
        double averageMS = 0;
        if (testCount > 0) {
            averageMS = (sumMS / testCount * 10.0 + 0.5) / 10.0f;
            System.out.println("\n平均成绩" + averageMS + "毫秒\n");
        } else {
            System.out.printf("没有有效成绩");
        }
        return averageMS;
    }


    // 单线程桶排序
    public static void countSort(Integer arr[]) {
        int len = arr.length;
        int max = arr[0];
        for (int i = 1; i != len; ++i) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int countArraySize = max + 1;
        int[] countArray = new int[countArraySize]; //malloc+memset效率高于calloc

        // 分配数据
        for (int i = 0; i < len; ) {
            countArray[arr[i++]]++;
        }
        // 收集数据
        int z = 0;
        for (int i = 0; i < countArraySize; ++i) {
            if (0 == countArray[i])
                continue;
            int j = -1;
            while (++j < countArray[i])
                arr[z++] = i;
        }
    }

    public static void main(String[] args) {

        Map<SortFunction<Integer>, String> funcNameMap = new HashMap<>();
        funcNameMap.put(Sorts::countSort, "桶排");
        funcNameMap.put(Sorts::quickSort, "快排");

        funcNameMap.forEach((func, name) -> {
            System.out.println(name);
            benchmark(func, 50000, 50);
        });

    }


    //求数据的最大位数
    private static int _maxBite(int arr[]) {
        int len = arr.length;
        int bite = 1;
        int first = 10;
        int i;
        for (i = 0; i < len; i++) {
            while (arr[i] >= first) {
                first *= 10;
                ++bite;
            }
        }
        return bite;
    }

    public static void radixSort(int arr[]) {
        int len = arr.length;
        int bite = _maxBite(arr);
        int[] temp = new int[len];
        int[] count = new int[10];//计数器
        int i, j, k;
        int radix = 1;
        for (i = 1; i <= bite; i++) {

            //每次分配前清空计数器
            for (j = 0; j < 10; j++)
                count[j] = 0;

            //统计每个桶中的记录数
            for (j = 0; j < len; j++) {
                k = (arr[j] / radix) % 10;
                ++count[k];
            }

            //将temp中的位置依次分配给每个桶
            for (j = 1; j < 10; j++)
                count[j] += count[j - 1];

            //将所有桶中记录依次收集到temp中
            for (j = len - 1; j >= 0; j--) {
                k = (arr[j] / radix) % 10;
                --count[k];
                temp[count[k]] = arr[j];
            }

            //将临时数组的内容复制到arr[]中
            for (j = 0; j < len; j++)
                arr[j] = temp[j];
            radix *= 10;
        }
    }

    @FunctionalInterface
    interface SortFunction<T extends Comparable<T>> {
        void sort(T[] arr);
    }
}
