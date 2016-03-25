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

    @Test
    public void test() {
        Assert.assertEquals(2, lengthOfLongestSubstring("au"));
        Assert.assertEquals(2, lengthOfLongestSubstring("abba"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(8, lengthOfLongestSubstring("qpxrjxkltzyx"));
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
        int right = 1;

        hash[s.charAt(0)] = 0;
        while (right < length) {
            if (hash[s.charAt(right)] >= left) {
                left = hash[s.charAt(right)] + 1;
            }
            max = Math.max(max, right - left + 1);
            hash[s.charAt(right)] = right;
            right++;
        }
        return max;
    }
}
