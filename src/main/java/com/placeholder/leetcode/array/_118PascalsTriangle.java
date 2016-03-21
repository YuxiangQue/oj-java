package com.placeholder.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _118PascalsTriangle {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.com/problems/pascals-triangle/
     * Given numRows, generate the first numRows of Pascal's triangle.
     * <p/>
     * For example, given numRows = 5,
     * Return
     * <p/>
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows <= 0)
            return triangle;
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        for (int i = 2; i <= numRows; ++i) {
            triangle.add(new ArrayList<>());
            List<Integer> prevRow = triangle.get(i - 2);
            List<Integer> row = triangle.get(i - 1);
            row.add(1);
            for (int j = 1; j < i - 1; ++j) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
        }
        return triangle;
    }
}
