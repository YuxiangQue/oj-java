package com.placeholder.leetcode.trie;

/**
 * Created by yuxiangque on 2016/3/21.
 */
public class _211AddAndSearchWord {

    public static class WordDictionary {

        private TrieNode root = new TrieNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null)
                    node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.count += 1;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null)
                    return false;
                node = node.children[index];
            }
            return node.count > 0;
        }

        static class TrieNode {
            // Initialize your data structure here.

            TrieNode[] children = new TrieNode[26]; // 各个子节点
            int count = 0;
        }
    }
}
