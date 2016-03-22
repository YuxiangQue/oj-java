package com.placeholder.leetcode.stack;

import java.util.Stack;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _150EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "*": {
                    String num1 = stack.pop();
                    String num0 = stack.pop();
                    stack.push(String.valueOf(Integer.valueOf(num0) * Integer.valueOf(num1)));
                    break;
                }
                case "/": {
                    String num1 = stack.pop();
                    String num0 = stack.pop();
                    stack.push(String.valueOf(Integer.valueOf(num0) / Integer.valueOf(num1)));
                    break;
                }
                case "+": {
                    String num1 = stack.pop();
                    String num0 = stack.pop();
                    stack.push(String.valueOf(Integer.valueOf(num0) + Integer.valueOf(num1)));
                    break;
                }
                case "-": {
                    String num1 = stack.pop();
                    String num0 = stack.pop();
                    stack.push(String.valueOf(Integer.valueOf(num0) - Integer.valueOf(num1)));
                    break;
                }
                default:
                    stack.push(token);
                    break;
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        evalRPN(new String[]{"2", "1", "+", "3", "*"});
        evalRPN(new String[]{"4", "3", "-"});
        evalRPN(new String[]{"0", "3", "/"});
    }
}
