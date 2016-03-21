package com.placeholder.leetcode.string;

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */
public class _6ZigZagConversion {

    public static void main(String[] args) {
        new _6ZigZagConversion().convert("PAYPALISHIRING", 3);
    }

    /**
     * http://www.cnblogs.com/sanghai/p/3632528.html
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {

        if (s.length() == 0 || numRows <= 0)
            return "";
        if (numRows == 1)
            return s;

        int length = s.length();

        // 每个循环中的元素个数
        int roundSize = 2 * numRows - 2;

        // 列数
        int numColumns = (int) Math.ceil(length * 1.0 / roundSize) * (numRows - 1);// 循环数乘以每个循环的列数目

        char[][] map = new char[numRows][numColumns];

        int index = 0;
        for (; ; ) {

            int roundIndex = index / roundSize;

            // 向下循环:nRows
            for (int j = 0; j < numRows; ++j) {
                if (index >= length)
                    break;
                map[j][roundIndex * (numRows - 1)] = s.charAt(index);
                ++index;
            }

            // 斜角线循环:nRows-2(减去首尾两个端点)
            for (int j = 0; j < numRows - 2; ++j) {
                if (index >= length)
                    break;
                map[numRows - j - 2][roundIndex * (numRows - 1) + 1 + j] = s.charAt(index);
                ++index;
            }

            if (index >= length)
                break;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (map[i][j] != '\0') {
                    sb.append(map[i][j]);
                }
            }
        }

        return sb.toString();
    }
}
