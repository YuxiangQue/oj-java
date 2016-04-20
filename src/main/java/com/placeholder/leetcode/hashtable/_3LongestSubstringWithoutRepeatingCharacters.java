package com.placeholder.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * http://blog.csdn.net/likecool21/article/details/10858799
 *
 * @author yuxiangque
 * @version 2016/3/25
 */
public class _3LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring2(String s) {
        char[] string = s.toCharArray();
        int[] m = new int[128];
        int counter = 0;
        int begin = 0;
        int end = 0;
        int maxLength = 0;

        while (end < s.length()) {
            if (m[string[end]] > 0) {
                ++counter;
            }
            ++m[string[end]];
            ++end;

            while (counter > 0) {
                if (m[string[begin]] > 1) {
                    --counter;
                }
                --m[string[begin]];
                ++begin;
            }
            if (end - begin > maxLength) {
                maxLength = end - begin;
            }
        }
        return maxLength;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, lengthOfLongestSubstring2("au"));
        Assert.assertEquals(2, lengthOfLongestSubstring2("abba"));
        Assert.assertEquals(1, lengthOfLongestSubstring2("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring2("abcabcbb"));
        Assert.assertEquals(8, lengthOfLongestSubstring2("qpxrjxkltzyx"));
    }

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        int[] hash = new int[256];
        Arrays.fill(hash, -1);
        int max = 1;
        int left = 0;
        hash[s.charAt(0)] = 0;
        for (int right = 1; right < length; ++right) {
            char ch = s.charAt(right);
            if (hash[ch] >= left) {  //
                left = hash[ch] + 1;
            }
            max = Math.max(max, right - left + 1);
            hash[ch] = right;
        }
        return max;
    }
}
