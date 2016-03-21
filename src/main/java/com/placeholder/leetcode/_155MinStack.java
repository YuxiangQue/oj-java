package com.placeholder.leetcode;

import java.util.Stack;
import java.util.jar.Pack200;

/**
 * Created by yuxiangque on 2016/3/14.
 */
public class _155MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        minStack.push(4);
        minStack.push(4);
        minStack.push(3);
        minStack.push(2);
        minStack.push(1);
        minStack.push(0);
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        return;
    }

    static class MinStack {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int x) {
            stack.push(x);
            if (!minStack.empty()) {
                int min = minStack.peek();
                if (x <= min) {
                    minStack.push(x);
                }
            } else {
                minStack.push(x);
            }
        }

        public void pop() {
            int min = minStack.peek();
            int top = stack.peek();
            if (min == top) {
                minStack.pop();
                stack.pop();
            } else {
                stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
