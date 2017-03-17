package com.placeholder.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * http://bookshadow.com/weblog/2016/08/21/leetcode-lexicographical-numbers/
 */
public class _386LexicographicalNumbers {

    public static void helper(int n, int m, List<Integer> result) {
        result.add(m);
        if (m * 10 <= n) {
            helper(n, m * 10, result);
        }
        if (m < n && m % 10 < 9) {
            helper(n, m + 1, result);
        }
    }

    public static List<Integer> helperStack(int n) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (!stack.empty()) {
            int m = stack.pop();
            result.add(m);
            if (m < n && m % 10 < 9) {
                stack.push(m + 1);
            }
            if (m * 10 <= n) {
                stack.push(m * 10);
            }
        }
        return result;
    }


    public static List<Integer> lexicalOrder(int n) {
        return helperStack(n);
    }

    public static void main(String[] args) {
        System.out.println(lexicalOrder(14));
    }
}
