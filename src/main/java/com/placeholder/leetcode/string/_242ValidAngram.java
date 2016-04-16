package com.placeholder.leetcode.string;

/**
 * @author yuxiangque
 * @version 2016/4/16
 */
public class _242ValidAngram {
    boolean isAnagram(String s, String t) {
        int[] countArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++countArray[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < s.length(); i++) {
            --countArray[t.charAt(i) - 'a'];
        }
        for (int count : countArray) {
            if (count != 0)
                return false;
        }
        return true;
    }
}
