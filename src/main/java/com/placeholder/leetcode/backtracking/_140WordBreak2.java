package com.placeholder.leetcode.backtracking;

import java.util.*;

/**
 * http://oj.leetcode.com/problems/word-break-ii/
 * http://blog.csdn.net/linhuanmars/article/details/22452163
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _140WordBreak2 {


    public static void main(String[] args) {
        Set<String> wordDict = new HashSet<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        Long start = System.currentTimeMillis();
        System.out.println(new _2().wordBreak("catsanddog", wordDict));
        System.out.println(System.currentTimeMillis() - start);

        wordDict = new HashSet<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        wordDict.add("ba");
        start = System.currentTimeMillis();
        System.out.println(new _2().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", wordDict));
        System.out.println(System.currentTimeMillis() - start);
    }

    // dfs
    static class _2 {

        public List<String> wordBreak(String s, Set<String> wordDict) {
            List<String> sentenses = new ArrayList<>();
            if (s.length() == 0)
                return sentenses;
            Map<String, Boolean> wordMap = new HashMap<>();
            for (String word : wordDict) {
                wordMap.put(word, false);
            }
            dfs(s, 0, wordMap, new ArrayList<>(), sentenses);
            return sentenses;
        }

        private void dfs(String s,
                         int index,
                         Map<String, Boolean> wordMap,
                         List<String> sentense,
                         List<String> sentenses) {
            int length = s.length();
            if (index == length) {
                StringBuilder sb = new StringBuilder();
                sb.append(sentense.get(0));
                for (int i = 1; i < sentense.size(); ++i) {
                    sb.append(' ');
                    sb.append(sentense.get(i));
                }
                sentenses.add(sb.toString());
                return;
            }
            Set<String> wordSet = wordMap.keySet();
            for (String word : wordSet) {
                if (wordMap.get(word)) {
                    continue;
                }
                if (index + word.length() > s.length()) {
                    continue;
                }
                String word1 = s.substring(index, index + word.length());
                if (word.equals(word1)) {
                    wordMap.put(word, true);
                    sentense.add(word);
                    dfs(s, index + word.length(), wordMap, sentense, sentenses);
                    sentense.remove(sentense.size() - 1);
                    wordMap.put(word, false);
                }
            }
        }
    }

    // dfs
    static class _1 {

        public List<String> wordBreak(String s, Set<String> wordDict) {
            List<String> sentenses = new ArrayList<>();
            if (s.length() == 0)
                return sentenses;
            Set<Character> charSet = new HashSet<>();
            Map<String, Boolean> wordMap = new HashMap<>();
            int maxWordLength = 0;
            int minWordLength = s.length();
            for (String word : wordDict) {
                wordMap.put(word, false);
                for (int i = 0; i < word.length(); ++i) {
                    charSet.add(word.charAt(i));
                }
                maxWordLength = maxWordLength > word.length() ? maxWordLength : word.length();
                minWordLength = minWordLength < word.length() ? minWordLength : word.length();
            }
            for (int i = 0; i < s.length(); ++i) {
                if (!charSet.contains(s.charAt(i)))
                    return sentenses;
            }
            helper(s, 0, wordMap, maxWordLength, minWordLength, new ArrayList<>(), sentenses);
            return sentenses;
        }

        private void helper(String s,
                            int index,
                            Map<String, Boolean> wordMap,
                            int maxWordLength,
                            int minWordLength,
                            List<String> sentense,
                            List<String> sentenses) {
            int length = s.length();
            if (index == length) {
                StringBuilder sb = new StringBuilder();
                sb.append(sentense.get(0));
                for (int i = 1; i < sentense.size(); ++i) {
                    sb.append(' ');
                    sb.append(sentense.get(i));
                }
                sentenses.add(sb.toString());
                return;
            }
            for (int wordLength = minWordLength; wordLength <= maxWordLength && wordLength <= length - index; ++wordLength) {
                String word = s.substring(index, index + wordLength);
                if (wordMap.get(word) != null && !wordMap.get(word)) {
                    wordMap.put(word, true);
                    sentense.add(word);
                    helper(s, index + wordLength, wordMap, maxWordLength, minWordLength, sentense, sentenses);
                    sentense.remove(sentense.size() - 1);
                    wordMap.put(word, false);
                }
            }
        }
    }
}
