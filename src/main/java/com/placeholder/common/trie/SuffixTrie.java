package com.placeholder.common.trie;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuxiangque
 * @version 2016/4/14
 */
public class SuffixTrie {

    private Node root;

    public SuffixTrie(String word) {
        root = new Node();
        for (int index = 0; index < word.length(); index++) {
            insert(word, index);
        }
    }

    /**
     * @param word
     * @param startIndex
     */
    private void insert(String word, int startIndex) {
        Node node = root;
        for (int index = startIndex; index < word.length(); ++index) {
            char key = word.charAt(index);
            if (node.children == null) {
                node.children = new HashMap<>();
            }
            Node child = node.children.get(key);
            if (child == null) {
                child = new Node();
                child.value = key;
                node.children.put(key, child);
            }
            child.indexes.add(index);
            node = child;
        }
    }

    /**
     * @param prefix
     * @return
     */
    public List<Integer> search(String prefix) {
        List<Integer> list = new ArrayList<>();
        Node node = root;
        for (int i = 0; i < prefix.length(); ++i) {
            if (node.children == null)
                return list;
            Node child = node.children.get(prefix.charAt(i));
            if (child == null)
                return list;
            node = child;
        }
        list.addAll(new ArrayList<>(node.indexes));
        return list;
    }

    public static class Tester {
        @Test
        public void test() {
            SuffixTrie suffixTrie = new SuffixTrie("BIBS");
            Assert.assertEquals(1, suffixTrie.search("BI").size());
            Assert.assertEquals(1, (int) suffixTrie.search("BI").get(0));
            Assert.assertEquals(2, suffixTrie.search("B").size());
            Assert.assertEquals(0, (int) suffixTrie.search("B").get(0));
            Assert.assertEquals(2, (int) suffixTrie.search("B").get(1));
        }
    }

    private static class Node {
        Map<Character, Node> children; // 各个子节点
        List<Integer> indexes = new ArrayList<>();
        char value;
    }
}