package com.placeholder.jianzhioffer;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        char[] original = str.toString().toCharArray();

        // Count number of backspaces
        int counter = 0;
        for (char ch : original) {
            if (ch == ' ') {
                ++counter;
            }
        }

        char[] destination = new char[original.length + counter * 2];
        counter = 0;
        for (char ch : original) {
            if (ch == ' ') {
                destination[counter++] = '%';
                destination[counter++] = '2';
                destination[counter++] = '0';
            } else {
                destination[counter++] = ch;
            }
        }
        return String.valueOf(destination);
    }
}
