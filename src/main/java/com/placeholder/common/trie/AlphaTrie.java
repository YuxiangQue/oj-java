package com.placeholder.common.trie;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class AlphaTrie {

    private Node root;

    public AlphaTrie() {
        root = new Node();
    }

    // Inserts a word into the alphaTrie.
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); ++i) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null)
                node.children[index] = new Node();
            node = node.children[index];
        }
        node.count += 1;
    }

    // Returns if the word is in the alphaTrie.
    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); ++i) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        }
        return node.count > 0;
    }

    // Returns if there is any word in the alphaTrie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); ++i) {
            int index = prefix.charAt(i) - 'a';
            if (node.children[index] == null)
                return false;
            node = node.children[index];
        }
        return true;
    }

    static class Node {
        // Initialize your data structure here.

        Node[] children = new Node[26]; // 各个子节点
        int count = 0;
    }
}