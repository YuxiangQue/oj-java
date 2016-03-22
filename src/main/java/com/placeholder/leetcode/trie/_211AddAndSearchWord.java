package com.placeholder.leetcode.trie;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _211AddAndSearchWord {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        //wordDictionary.addWord("a");
        //wordDictionary.search(".");
        wordDictionary.addWord("bbb");
        System.out.println(wordDictionary.search(".bb"));
        System.out.println(wordDictionary.search("b.b"));
        System.out.println(wordDictionary.search("bb."));
        System.out.println(wordDictionary.search("b.."));
        System.out.println(wordDictionary.search("..b"));
        System.out.println(wordDictionary.search(".b."));
        System.out.println(wordDictionary.search(".b.."));
    }

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
            return dfs(root, word, 0);
        }

        private boolean dfs(TrieNode parentNode, String word, int currentWordIndex) {
            if (currentWordIndex == word.length() - 1) {
                char ch = word.charAt(currentWordIndex);
                if (ch == '.') {
                    for (int i = 0; i < 26; ++i) {
                        TrieNode child = parentNode.children[i];
                        if (child != null && child.count > 0)
                            return true;
                    }
                    return false;
                } else {
                    TrieNode currentNode = parentNode.children[ch - 'a'];
                    return currentNode != null && currentNode.count > 0;
                }
            }
            char ch = word.charAt(currentWordIndex);
            if (ch == '.') {  // need backtracking
                for (int i = 0; i < 26; ++i) {
                    TrieNode child = parentNode.children[i];
                    if (child != null && dfs(child, word, currentWordIndex + 1))
                        return true;
                }
                return false;
            } else {
                TrieNode child = parentNode.children[ch - 'a'];
                if (child != null && dfs(child, word, currentWordIndex + 1))
                    return true;
            }
            return false;
        }

        static class TrieNode {
            // Initialize your data structure here.
            TrieNode[] children = new TrieNode[26]; // 各个子节点
            int count = 0;
        }
    }
}
