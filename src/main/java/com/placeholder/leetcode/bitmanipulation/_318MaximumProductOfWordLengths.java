package com.placeholder.leetcode.bitmanipulation;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _318MaximumProductOfWordLengths {

    public static int wordToLetterMap(String word) {
        int letterMap = 0;
        for (int index = 0; index < word.length(); ++index) {
            letterMap |= 1 << (word.charAt(index) - 'a');
        }
        return letterMap;
    }

    public static int maxProduct(String[] words) {
        List<Integer> letterMaps = new ArrayList<>();
        for (String word : words) {
            letterMaps.add(wordToLetterMap(word));
        }
        int maxProduct = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((letterMaps.get(i) & letterMaps.get(j)) == 0) {
                    int product = words[i].length() * words[j].length();
                    if (product > maxProduct)
                        maxProduct = product;
                }
            }
        }
        return maxProduct;
    }

    @Test
    public void test() {
        Assert.assertEquals(16, maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
        Assert.assertEquals(4, maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        Assert.assertEquals(0, maxProduct(new String[]{"a", "aa", "aaa", "aaaa"}));
    }
}

