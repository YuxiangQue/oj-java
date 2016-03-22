package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _17LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        System.out.println(new _17LetterCombinationsOfAPhoneNumber().letterCombinations(""));
        System.out.println(new _17LetterCombinationsOfAPhoneNumber().letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0)
            return combinations;
        List<String> candidates = buildCandidates();
        dfs(digits, 0, candidates, new StringBuilder(), combinations);
        return combinations;
    }

    private void dfs(String digits, int digitIndex, List<String> candidates, StringBuilder sb, List<String> combinations) {
        if (digitIndex == digits.length()) {
            combinations.add(sb.toString());
            return;
        }
        char digit = digits.charAt(digitIndex);
        String candidate = candidates.get(digit - '0');
        int candidateLength = candidate.length();
        for (int i = 0; i < candidateLength; ++i) {
            sb.append(candidate.charAt(i));
            dfs(digits, digitIndex + 1, candidates, sb, combinations);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private List<String> buildCandidates() {
        List<String> map = new ArrayList<>();
        map.add(""); // 0
        map.add(""); // 1
        map.add("abc");
        map.add("def");
        map.add("ghi");
        map.add("jkl");
        map.add("mno");
        map.add("pqrs"); //  7
        map.add("tuv");
        map.add("wxyz"); // 9
        return map;
    }
}
