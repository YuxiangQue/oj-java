package com.placeholder.common;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class TrieMap<V> {

    private TrieNode<V> root;

    public TrieMap() {
        root = new TrieNode<V>();
    }

    // Inserts a word into the trie.
    public void put(String key, V value) {
        TrieNode node = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i);
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.value = value;
    }

    // Returns if the word is in the trie.
    public V get(String key) {
        TrieNode<V> node = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i);
            if (node.children[index] == null)
                return null;
            node = node.children[index];
        }
        return node.value;
    }

    public V remove(String key) {
        TrieNode<V> node = root;
        for (int i = 0; i < key.length(); ++i) {
            int index = key.charAt(i);
            if (node.children[index] == null)
                return null;
            node = node.children[index];
        }
        V value = node.value;
        node.value = null;
        return value;
    }

    static class TrieNode<V> {
        // Initialize your data structure here.
        TrieNode<V>[] children = new TrieNode[255]; // 各个子节点
        V value = null;
    }
}