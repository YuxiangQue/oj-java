package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * @author yuxiangque
 * @version 2016/3/23
 */
public class _22GenerateParentheses {

    void dfs(String parenthesis,
             int left,
             int right, List<String> combinations) {
        if (left == 0 && right == 0) {
            combinations.add(parenthesis);
        } else if (left == right && left > 0) {
            dfs(parenthesis + "(", left - 1, right, combinations);
        } else if (left < right) {
            if (left > 0) {
                dfs(parenthesis + "(", left - 1, right, combinations);
            }
            if (right > 0) {
                dfs(parenthesis + ")", left, right - 1, combinations);
            }
        }
    }

    List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        dfs("", n, n, combinations);
        return combinations;
    }
}
