package com.placeholder.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _119PascalsTriangle2 {
    public static void main(String[] args) {
        System.out.println(new _119PascalsTriangle2().getRow(0));
        System.out.println(new _119PascalsTriangle2().getRow(1));
        System.out.println(new _119PascalsTriangle2().getRow(2));
        System.out.println(new _119PascalsTriangle2().getRow(3));
        System.out.println(new _119PascalsTriangle2().getRow(4));
        System.out.println(new _119PascalsTriangle2().getRow(5));
        System.out.println(new _119PascalsTriangle2().getRow(6));
    }

    public List<Integer> getRow(int rowIndex) {
        ++rowIndex;
        List<Integer> row = new ArrayList<>();
        List<Integer> prevRow = new ArrayList<>();
        if (rowIndex <= 0)
            return row;
        prevRow.add(1);
        for (int i = 2; i <= rowIndex; ++i) {
            row.add(1);
            for (int j = 1; j < i - 1; ++j) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            List<Integer> tmp = prevRow; // reuse space
            prevRow = row;
            row = tmp;
            row.clear();
        }
        return prevRow;
    }
}
