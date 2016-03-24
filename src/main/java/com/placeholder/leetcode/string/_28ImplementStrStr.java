package com.placeholder.leetcode.string;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _28ImplementStrStr {

    public static int strStr(String haystack, String needle) {
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        if (needleLength > haystackLength)
            return -1;
        for (int i = 0; i <= haystackLength - needleLength; i++) {
            boolean matched = true;
            for (int j = 0; j < needleLength; ++j) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        assertEquals(0, strStr("Hello, World", "H"));
        assertEquals(11, strStr("Hello, World", "d"));
        assertEquals(-1, strStr("Hello, World", "ldd"));
    }
}
