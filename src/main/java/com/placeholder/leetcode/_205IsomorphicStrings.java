package com.placeholder.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 阙宇翔
 * @version 2016/2/15
 */
public class _205IsomorphicStrings {

    public static boolean isIsomorphic1(String s, String t) {
        int length = s.length();
        Map<Character, Integer> index = new HashMap<>();
        for (Integer i = 0; i < length; ++i)
            // 判断上次出现对应模式的下标是否相同
            if (!Objects.equals(index.put(s.charAt(i), i), index.put(t.charAt(i), i)))
                return false;
        return true;
    }

    public static boolean isIsomorphic(String s, String t) {
        int length = s.length();
        // 两个方向的HashMap
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < length; ++i) {
            if (!map1.containsKey(s.charAt(i))) {
                map1.put(s.charAt(i), t.charAt(i));
            } else {
                if (!Objects.equals(map1.get(s.charAt(i)), t.charAt(i))) {
                    return false;
                }
            }
            if (!map2.containsKey(t.charAt(i))) {
                map2.put(t.charAt(i), s.charAt(i));
            } else {
                if (!Objects.equals(map2.get(t.charAt(i)), s.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic1("egg", "add"));
        System.out.println(!isIsomorphic1("foo", "bar"));
        System.out.println(isIsomorphic1("paper", "title"));
        System.out.println(!isIsomorphic1("ab", "aa"));
        System.out.println(!isIsomorphic1("aa", "ab"));
        System.out.println(isIsomorphic1("a", "a"));

    }
}
