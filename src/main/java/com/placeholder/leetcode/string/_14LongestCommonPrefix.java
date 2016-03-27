package com.placeholder.leetcode.string;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * @author 阙宇翔
 * @version 2016/3/14
 */
public class _14LongestCommonPrefix {

    public static String longestCommonPrefix(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int len = len1 < len2 ? len1 : len2;
        int commonPrefixLen = 0;
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
            ++commonPrefixLen;
        }
        return str1.substring(0, commonPrefixLen);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int count = strs.length;
        String commonPrefix = strs[0];
        for (int i = 1; i < count; i++) {
            commonPrefix = longestCommonPrefix(commonPrefix, strs[i]);
            if (commonPrefix.equals("")) {
                return "";
            }
        }
        return commonPrefix;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{}));
        System.out.println(longestCommonPrefix(new String[]{"What", "Whattttt"}));
        System.out.println(longestCommonPrefix(new String[]{"What", "Whattttt", "Whattttt"}));
    }
}
