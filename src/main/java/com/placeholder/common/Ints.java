package com.placeholder.common;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class Ints {
    public static ArrayList<Integer> asList(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(i, nums[i]);
        }
        return list;
    }

    public static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(2);
        int tmp = pq.peek();
        return;
    }
}
