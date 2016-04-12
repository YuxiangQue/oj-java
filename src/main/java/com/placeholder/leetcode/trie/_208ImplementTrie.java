package com.placeholder.leetcode.trie;

import com.placeholder.common.Trie;

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
    // trie.put("somestring");
    // trie.get("key");
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("something");
        trie.insert("some");
        System.out.println(trie.search("some"));
        System.out.println(trie.search("somet"));
        System.out.println(trie.startsWith("somet"));
    }
}
