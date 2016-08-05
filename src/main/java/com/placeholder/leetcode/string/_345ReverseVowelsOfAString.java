package com.placeholder.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/8/5
 */
public class _345ReverseVowelsOfAString {

    private static final char[] vowels = "aeiouAEIOU".toCharArray();

    public boolean isVowel(char ch) {
        for (char vowel : vowels) {
            if (ch == vowel)
                return true;
        }
        return false;
    }

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            while (left < right && !isVowel(chars[left]))
                ++left;
            while (left < right && !isVowel(chars[right]))
                --right;
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            ++left;
            --right;
        }
        return String.valueOf(chars);
    }

    @Test
    public void test() {
        Assert.assertEquals("holle", reverseVowels("hello"));
        Assert.assertEquals("leotcede", reverseVowels("leetcode"));
    }
}
