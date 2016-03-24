package com.placeholder.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _151ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+", -1);
        if (words.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        int index = words.length - 1;
        sb.append(words[index--]);
        for (; index >= 0; --index) {
            sb.append(" ").append(words[index]);
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("1", reverseWords(" 1"));
        Assert.assertEquals("1", reverseWords("1 "));
        Assert.assertEquals("World Hello,", reverseWords("Hello, World"));
    }
}
