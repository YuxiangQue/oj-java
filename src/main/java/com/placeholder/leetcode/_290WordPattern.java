package com.placeholder.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.com/problems/word-pattern/
 *
 * @author 阙宇翔
 * @version 2016/2/15
 */
public class _290WordPattern {

    /**
     * String pattern = "abba";
     * String str = "dog cat cat dog";
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern1(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
            // 判断上次出现对应模式的下标是否相同
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }


    public static boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        int length = pattern.length();
        if (length != words.length)
            return false;
        // 两个方向的HashMap
        HashMap<Character, String> map1 = new HashMap<>(length);
        HashMap<String, Character> map2 = new HashMap<>(length);
        for (int i = 0; i < length; ++i) {
            if (!map1.containsKey(pattern.charAt(i))) {
                map1.put(pattern.charAt(i), words[i]);
            } else {
                if (!Objects.equals(map1.get(pattern.charAt(i)), words[i])) {
                    return false;
                }
            }
            if (!map2.containsKey(words[i])) {
                map2.put(words[i], pattern.charAt(i));
            } else {
                if (!Objects.equals(map2.get(words[i]), pattern.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";
        System.out.println(wordPattern(pattern, str));

        pattern = "abba";
        str = "dog dog dog dog";
        System.out.println(!wordPattern(pattern, str));

        pattern = "abba";
        str = "dog cat cat fish";
        System.out.println(!wordPattern(pattern, str));

        pattern = "";
        str = "beef";
        System.out.println(!wordPattern(pattern, str));
    }
}
