package com.placeholder.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 *
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _171ExcelSheetColumnNumber {

    @Test
    public void test() {
        Assert.assertEquals(1, titleToNumber("A"));
        Assert.assertEquals(26, titleToNumber("Z"));
        Assert.assertEquals(27, titleToNumber("AA"));
        Assert.assertEquals(28, titleToNumber("AB"));
    }

    // 进制转换
    public int titleToNumber(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('A' + i), i + 1);
        }
        int number = 0;
        for (int index = 0; index < s.length(); index++) {
            number = number * 26 + map.get(s.charAt(index));
        }
        return number;
    }
}
