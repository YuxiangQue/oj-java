package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _131PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(new _131PalindromePartitioning().partition("aab"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
        if (s.length() == 0) {
            return partitions;
        }
        List<String> partition = new ArrayList<>();
        helper(s, 0, partition, partitions);
        return partitions;
    }

    private void helper(String s, int startIndex, List<String> partition, List<List<String>> partitions) {
        if (startIndex == s.length()) {
            partitions.add(new ArrayList<>(partition));
            return;
        }
        int length = s.length();
        for (int substrLength = 1; substrLength <= length - startIndex; ++substrLength) {
            String substr = s.substring(startIndex, startIndex + substrLength);
            partition.add(substr);
            if (isPalindrome(substr)) {
                helper(s, startIndex + substrLength, partition, partitions);
            }
            partition.remove(partition.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int length = s.length();
        for (int i = 0; i < length / 2; ++i) {
            if (s.charAt(i) != s.charAt(length - i - 1))
                return false;
        }
        return true;
    }
}

