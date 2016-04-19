package com.placeholder.common;

import java.util.Comparator;

/**
 * @author yuxiangque
 * @version 2016/4/15
 */
public class Heap<T> {
    Comparator<T> comparator;
    T[] arr;
    int capacity;
    int size;

    public Heap(int capacity, Comparator<T> comparator) {
        this.arr = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(100, Integer::compareTo);
        heap.offer(1);
        heap.offer(2);
        heap.offer(3);
        System.out.println(heap.peek());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }

    // max heap by default
    private static <T> void siftDown(int root, T[] arr, int size, Comparator<T> comparator) {
        if (root < 0 || root > size - 1)
            return;
        int child;
        T x = arr[root];
        for (; 2 * root + 1 < size; root = child) {
            child = 2 * root + 1;
            if (child != size - 1 && comparator.compare(arr[child], arr[child + 1]) < 0) {
                child += 1;
            }
            if (comparator.compare(x, arr[child]) < 0) {
                arr[root] = arr[child];
            } else {
                break;
            }
        }
        arr[root] = x;
    }

    // max heap by default
    private static <T> void siftUp(int root, T[] arr, int size, Comparator<T> comparator) {
        if (root < 0 || root > size - 1)
            return;
        int parent;
        T x = arr[root];
        for (; root != 0 && root / 2 >= 0; root = parent) {
            parent = root / 2;
            if (comparator.compare(arr[parent], x) < 0) {
                arr[root] = arr[parent];
            } else {
                break;
            }
        }
        arr[root] = x;
    }

    // max heap by default
    public static <T extends Comparable<T>> void siftDown(T[] arr, int root) {
        int capacity = arr.length;
        if (root < 0 || root > capacity - 1)
            return;
        int child;
        T x = arr[root];
        for (; 2 * root + 1 < capacity; root = child) {
            child = 2 * root + 1;
            if (child != capacity - 1 && arr[child].compareTo(arr[child + 1]) < 0) {
                child += 1;
            }
            if (x.compareTo(arr[child]) < 0) {
                arr[root] = arr[child];
            } else {
                break;
            }
        }
        arr[root] = x;
    }

    // max heap by default
    public static <T extends Comparable<T>> void siftUp(T[] arr, int root) {
        int capacity = arr.length;
        if (root < 0 || root > capacity - 1)
            return;
        int parent;
        T x = arr[root];
        for (; root / 2 > 0; root = parent) {
            parent = root / 2;
            if (arr[parent].compareTo(x) < 0) {
                arr[root] = arr[parent];
            } else {
                break;
            }
        }
        arr[root] = x;
    }

    //  将指定的元素插入此优先级队列
    public void offer(T val) {
        if (size >= capacity) {
            return;
        }
        arr[size++] = val;
        siftUp(size - 1, arr, size, comparator);
    }

    // 获取但不移除此队列的头；如果此队列为空，则返回 null
    public T peek() {
        if (size <= 0) {
            return null;
        }
        return arr[0];
    }

    // 获取并移除此队列的头，如果此队列为空，则返回 null
    public T poll() {
        if (size <= 0) {
            return null;
        }
        T peek = arr[0];
        arr[0] = arr[--size];
        siftDown(0, arr, size, comparator);
        return peek;
    }

}
