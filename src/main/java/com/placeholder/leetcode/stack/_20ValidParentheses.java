package com.placeholder.leetcode.stack;

import java.util.Stack;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _20ValidParentheses {
    public static void main(String[] args) {
        System.out.println(new _20ValidParentheses().isValid("()"));
        System.out.println(new _20ValidParentheses().isValid("()[]{}"));
        System.out.println(!new _20ValidParentheses().isValid("(]"));
        System.out.println(!new _20ValidParentheses().isValid("([)]"));
        System.out.println(!new _20ValidParentheses().isValid("]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (stack.empty())
                        return false;
                    if (stack.pop() != '(')
                        return false;
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ']':
                    if (stack.empty())
                        return false;
                    if (stack.pop() != '[')
                        return false;
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '}':
                    if (stack.empty())
                        return false;
                    if (stack.pop() != '{')
                        return false;
                    break;
            }
        }
        return stack.empty();
    }
}
