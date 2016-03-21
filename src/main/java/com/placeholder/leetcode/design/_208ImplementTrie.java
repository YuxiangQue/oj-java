package com.placeholder.leetcode.design;

/**
 * #Design
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * http://songlee24.github.io/2015/05/09/prefix-tree/
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _208ImplementTrie {


    // Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("something");
        trie.insert("some");
        System.out.println(trie.search("some"));
        System.out.println(trie.search("somet"));
        System.out.println(trie.startsWith("somet"));
    }

    static class TrieNode {
        // Initialize your data structure here.

        TrieNode[] children = new TrieNode[26]; // 各个子节点
        int count = 0;

        public TrieNode() {

        }
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null)
                    node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.count += 1;
        }

        // Returns if the word is in the trie.
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

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); ++i) {
                int index = prefix.charAt(i) - 'a';
                if (node.children[index] == null)
                    return false;
                node = node.children[index];
            }
            return true;
        }
    }
}
