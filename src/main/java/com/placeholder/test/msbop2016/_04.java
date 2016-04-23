package com.placeholder.test.msbop2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class _04 {

    static void dfs(String parenthesis,
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

    static String[] Puzzle(int n) {
        List<String> combinations = new ArrayList<>();
        dfs("", n, n, combinations);
        String[] array = new String[combinations.size()];
        for (int i = 0; i < combinations.size(); i++) {
            array[i] = combinations.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Puzzle(4)));
    }
}
