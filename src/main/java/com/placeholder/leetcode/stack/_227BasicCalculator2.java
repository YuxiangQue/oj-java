package com.placeholder.leetcode.stack;

import java.util.*;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _227BasicCalculator2 {
    private static String integer(String s, int[] index) {
        int startIndex = index[0];
        ++index[0];
        while (index[0] < s.length()) {
            char ch = s.charAt(index[0]);
            if (ch >= '0' && ch <= '9') {
                ++index[0];
            } else {
                break;
            }
        }
        return s.substring(startIndex, index[0]);
    }

    private static List<String> parse(String s) {
        List<String> tokens = new ArrayList<>();
        int index = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (ch == ' ') {
                ++index;
            }
            if (ch == '*' || ch == '/' || ch == '+' || ch == '-' || ch == '(' || ch == ')') {
                tokens.add(ch + "");
                ++index;
            } else if (ch >= '0' && ch <= '9') {
                int[] outIndex = new int[]{index};
                tokens.add(integer(s, outIndex));
                index = outIndex[0];
            }
        }
        return tokens;
    }


    /**
     * 将中缀表达式转化为后缀表达式
     * http://www.nowamagic.net/librarys/veda/detail/2307
     *
     * @param tokens
     * @return
     */
    private static List<String> infixToPostfix(List<String> tokens) {
        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
        Stack<String> operatorStack = new Stack<>();
        List<String> postfix = new ArrayList<>();
        for (String token : tokens) {
            if (priorityMap.containsKey(token)) {
                if (operatorStack.empty()) {
                    operatorStack.push(token);
                } else {
                    // 弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
                    while (true) {
                        if (operatorStack.empty()) {
                            break;
                        }
                        String peek = operatorStack.peek();
                        if (!priorityMap.containsKey(peek)) {
                            break;
                        }
                        if (priorityMap.get(peek) < priorityMap.get(token)) {
                            break;
                        }
                        postfix.add(operatorStack.pop());
                    }
                    operatorStack.push(token);
                }
            } else if (Objects.equals(token, "(")) {
                operatorStack.push("(");
            } else if (Objects.equals(token, ")")) {
                while (true) {
                    if (operatorStack.empty()) {
                        break;
                    }
                    String peek = operatorStack.peek();
                    if (Objects.equals(peek, "(")) {
                        operatorStack.pop();
                        break;
                    }
                    postfix.add(peek);
                    operatorStack.pop();
                }
            } else {
                postfix.add(token);
            }
        }
        while (!operatorStack.empty()) {
            postfix.add(operatorStack.pop());
        }
        return postfix;
    }

    static int evalRPN(List<String> tokens) {
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

    public static int calculate(String s) {
        return evalRPN(infixToPostfix(parse(s)));
    }

    public static void main(String[] args) {
        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("9+(3-1)*3+10/2"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
