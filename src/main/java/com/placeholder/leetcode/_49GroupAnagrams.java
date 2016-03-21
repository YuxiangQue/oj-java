package com.placeholder.leetcode;

import java.util.*;

/**
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _49GroupAnagrams {


    // https://leetcode.com/discuss/58561/share-my-short-java-solution
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String signature = String.valueOf(ca);
            if (!map.containsKey(signature))
                map.put(signature, new ArrayList<>());
            map.get(signature).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
