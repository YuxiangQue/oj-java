package com.placeholder.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 阙宇翔
 * @version 2016/2/24
 */
public class ImplementStackUsingQueues {
    class MyStack {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        // Push element x onto stack.
        public void push(int x) {
            Queue<Integer> empty, nonEmpty;
            if (queue1.isEmpty()) {
                empty = queue1;
                nonEmpty = queue2;
            } else {
                empty = queue2;
                nonEmpty = queue1;
            }
            nonEmpty.offer(x);
            while (!nonEmpty.isEmpty()) {
                empty.offer(nonEmpty.poll());
            }
        }

        // Removes the element on top of the stack.
        public void pop() {
            Queue<Integer> nonEmpty, empty;
            if (queue1.isEmpty()) {
                empty = queue1;
                nonEmpty = queue2;
            } else {
                empty = queue2;
                nonEmpty = queue1;
            }
            nonEmpty.poll();
        }

        // Get the top element.
        public int top() {
            Queue<Integer> nonEmpty;
            if (queue1.isEmpty()) {
                nonEmpty = queue2;

            } else {
                nonEmpty = queue1;
            }
            return nonEmpty.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }
}
