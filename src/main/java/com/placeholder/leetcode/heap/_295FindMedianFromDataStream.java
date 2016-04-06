package com.placeholder.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problemset/algorithms/
 *
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _295FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
        mf.addNum(4);
        System.out.println(mf.findMedian());
        mf.addNum(5);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        System.out.println(mf.findMedian());
    }

    static class MedianFinder {

        // maxHeap < minHeap
        // ... maxHeap[0]  minHeap[0] ...

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Comparator<Integer>) new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Adds a number into the data structure.
        public void addNum(int num) {
            int size = maxHeap.size() + minHeap.size();
            if ((size & 0x01) == 0) {  // even, add to minHeap
                // num < maxHeap[0]
                if (maxHeap.size() > 0 && num < maxHeap.peek()) {
                    maxHeap.offer(num);
                    num = maxHeap.poll();
                }
                minHeap.offer(num);
            } else { // odd, add to maxHeap
                if (minHeap.size() > 0 && num > minHeap.peek()) {
                    minHeap.offer(num);
                    num = minHeap.poll();
                }
                maxHeap.offer(num);
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            int size = maxHeap.size() + minHeap.size();
            if ((size & 0x01) == 0) {  // even
                return 0.5 * (maxHeap.peek() + minHeap.peek());
            } else { // odd
                return minHeap.peek();
            }
        }
    }


// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
}
