package com.placeholder.leetcode.hashtable;

import java.util.*;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _187RepeatedDnaSequence {
    public static List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int index = 0; index + 10 <= s.length(); ++index) {
            String key = s.substring(index, index + 10);
            Integer value = map.get(key);
            if (value == null) {
                map.put(key, 1);
            } else {
                map.put(key, value + 1);
            }
        }
        List<String> result = new ArrayList<>();
        map.forEach((key, value) -> {
            if (value >= 2)
                result.add(key);
        });
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAA"));


    }
}
