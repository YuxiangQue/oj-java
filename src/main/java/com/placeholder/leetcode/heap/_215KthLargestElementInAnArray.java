package com.placeholder.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * #Heap
 *
 * @author 阙宇翔
 * @version 2016/2/23
 */
public class _215KthLargestElementInAnArray {

    /**
     * 快排
     *
     * @param nums
     * @param k
     * @return
     */
    public static <T extends Comparable<T>> T findKthLargestQSort(T[] nums, int k) {
        if (k > nums.length)
            return null;
        return helper(nums, 0, nums.length - 1, k);
    }

    private static <T extends Comparable<T>> T helper(T[] nums, int begin, int end, int k) {
        if (begin >= end) {  // [begin begin+1)
            if (k - 1 == begin) {
                return nums[begin];
            }
        }
        int left = begin + 1;
        int right = end;
        while (true) {
            while (left < end && nums[left].compareTo(nums[begin]) < 0) {
                ++left;
            }
            while (right > begin && nums[right].compareTo(nums[begin]) > 0) {
                --right;
            }
            if (left > right)
                break;
            T tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            ++left;
            --right;
        }
        T tmp = nums[right];
        nums[right] = nums[begin];
        nums[begin] = tmp;

        if (k - 1 == right) {
            return nums[right];
        } else if (k - 1 < right) {
            return helper(nums, begin, right - 1, k);   // [0, right-1]  // k-1 <= right-0
        } else {
            return helper(nums, right + 1, end, k);     // [right+1, end]
        }
    }

    public static void main(String[] args) {
        System.out.println(new _215KthLargestElementInAnArray().findKthLargestHeap(new Integer[]{3, 2, 1, 5, 6, 4}, 2));
        Integer[] arr = new Integer[]{3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargestQSort(arr, 4));
        System.out.println(Arrays.toString(arr));

        arr = new Integer[]{3, 2, 1, 5, 6, 4, 9, 7, 11};
        System.out.println(findKthLargestQSort(arr, 9));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆
     *
     * @param nums
     * @param k
     * @return
     */
    public <T extends Comparable<T>> T findKthLargestHeap(T[] nums, int k) {
        PriorityQueue<T> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(nums).subList(0, k));
        for (int i = k; i < nums.length; ++i) {
            if (pq.peek().compareTo(nums[i]) < 0) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}
