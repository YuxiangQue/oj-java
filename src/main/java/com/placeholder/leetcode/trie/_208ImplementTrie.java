package com.placeholder.leetcode.trie;

import com.placeholder.common.trie.AlphaTrie;

/**
 * #Design
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * http://songlee24.github.io/2015/05/09/prefix-tree/
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _208ImplementTrie {


    // Your AlphaTrie object will be instantiated and called as such:
    // AlphaTrie trie = new AlphaTrie();
    // trie.put("somestring");
    // trie.get("key");
    public static void main(String[] args) {
        AlphaTrie alphaTrie = new AlphaTrie();
        alphaTrie.insert("something");
        alphaTrie.insert("some");
        System.out.println(alphaTrie.search("some"));
        System.out.println(alphaTrie.search("somet"));
        System.out.println(alphaTrie.startsWith("somet"));
    }
}
