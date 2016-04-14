package com.placeholder.common.trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuxiangque
 * @version 2016/4/14
 */
public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    // Inserts a word into the alphaTrie.
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); ++i) {
            char key = word.charAt(i);
            if (node.children == null) {
                node.children = new HashMap<>();
            }
            Node child = node.children.get(key);
            if (child == null) {
                child = new Node();
                node.children.put(key, child);
            }
            node = child;
        }
        node.count += 1;
    }

    // Returns if the word is in the alphaTrie.
    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); ++i) {
            if (node.children == null)
                return false;
            Node child = node.children.get(word.charAt(i));
            if (child == null)
                return false;
            node = child;
        }
        return node.count > 0;
    }

    // Returns if there is any word in the alphaTrie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); ++i) {
            if (node.children == null)
                return false;
            Node child = node.children.get(prefix.charAt(i));
            if (child == null)
                return false;
            node = child;
        }
        return true;
    }

    private static class Node {
        Map<Character, Node> children; // 各个子节点
        int count = 0;
    }

    public static class TrieTest {
        @Test
        public void test() {
            Trie trie = new Trie();
            trie.insert("HELLO");
            Assert.assertEquals(true, trie.startsWith("H"));
            Assert.assertEquals(true, trie.startsWith("HELLO"));
            Assert.assertEquals(false, trie.startsWith("HELLOH"));
            trie.insert("WORLD");
        }
    }
}
