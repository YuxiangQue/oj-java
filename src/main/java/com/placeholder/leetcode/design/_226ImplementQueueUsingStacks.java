package com.placeholder.leetcode.design;

import java.util.Stack;

/**
 * #Design
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _226ImplementQueueUsingStacks {
    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    private void transfer() {
        while (!pushStack.empty()) {
            popStack.push(pushStack.peek());
            pushStack.pop();
        }
    }


    // Push element x to the back of queue.
    void push(int x) {
        pushStack.push(x);
    }

    // Removes the element from in front of queue.
    void pop() {
        if (!popStack.empty()) {
            popStack.pop();
        } else {
            transfer();
            popStack.pop();
        }
    }

    // Get the front element.
    int peek() {
        if (!popStack.empty()) {
            return popStack.peek();
        } else {
            transfer();
            return popStack.peek();
        }
    }

    // Return whether the queue is empty.
    boolean empty() {
        return pushStack.empty() && popStack.empty();
    }
}
