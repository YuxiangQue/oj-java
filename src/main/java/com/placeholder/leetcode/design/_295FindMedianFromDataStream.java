package com.placeholder.leetcode.design;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://segmentfault.com/a/1190000003709954
 * #Heap
 * #Design
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _295FindMedianFromDataStream {

    class MedianFinder {

        // 新建最大堆
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((Comparator<Integer>) (o1, o2) -> o2 - o1);

        // 新建最小堆
        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        // Adds a number into the data structure.
        public void addNum(int num) {
            // 如果最大堆为空，或者该数小于最大堆堆顶，则加入最大堆
            if (maxheap.size() == 0 || num <= maxheap.peek()) {
                // 如果最大堆大小超过最小堆，则要平衡一下
                if (maxheap.size() > minheap.size()) {
                    minheap.offer(maxheap.poll());
                }
                maxheap.offer(num);
                // 数字大于最小堆堆顶，加入最小堆的情况
            } else if (minheap.size() == 0 || num > minheap.peek()) {
                if (minheap.size() > maxheap.size()) {
                    maxheap.offer(minheap.poll());
                }
                minheap.offer(num);
                // 数字在两个堆顶之间的情况
            } else {
                if (maxheap.size() <= minheap.size()) {
                    maxheap.offer(num);
                } else {
                    minheap.offer(num);
                }
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            // 返回大小较大的那个堆堆顶，如果大小一样说明是偶数个，则返回堆顶均值
            if (maxheap.size() > minheap.size()) {
                return maxheap.peek();
            } else if (maxheap.size() < minheap.size()) {
                return minheap.peek();
            } else if (maxheap.isEmpty() && minheap.isEmpty()) {
                return 0;
            } else {
                return (maxheap.peek() + minheap.peek()) / 2.0;
            }
        }
    }

}
