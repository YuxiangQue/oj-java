package com.placeholder.leetcode.divide_and_conqure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.cnblogs.com/ganganloveu/p/4681439.html
 *
 * @author yuxiangque
 * @version 2016/3/30
 */
public class _241DifferentWaysToAddParenthess {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < input.length(); index++) {
            char ch = input.charAt(index);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, index));
                List<Integer> right = diffWaysToCompute(input.substring(index + 1));
                for (Integer aLeft : left) {
                    for (Integer aRight : right) {
                        if (ch == '+') {
                            result.add(aLeft + aRight);
                        } else if (ch == '-') {
                            result.add(aLeft - aRight);
                        } else {
                            result.add(aLeft * aRight);
                        }
                    }
                }
            }
        }
        if (result.size() == 0)
            result.add(Integer.parseInt(input));
        return result;
    }

    @Test
    public void test() {
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }
}
