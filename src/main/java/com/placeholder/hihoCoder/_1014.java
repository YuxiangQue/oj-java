package com.placeholder.hihoCoder;

import java.util.Scanner;

/**
 * @author 阙宇翔
 * @version 2016/2/27
 */
public class _1014 {

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            trieTree.put(scanner.next());
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.println(trieTree.getPrefix(scanner.next()));
        }
    }

    static class TrieTree {

        TrieTreNode root = new TrieTreNode();

        public void put(String word) {
            TrieTreNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null)
                    node.children[word.charAt(i) - 'a'] = new TrieTreNode();
                node = node.children[word.charAt(i) - 'a'];
            }
            node.count += 1;
        }

        public int getPrefix(String word) {
            TrieTreNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    return 0;
                }
                node = node.children[word.charAt(i) - 'a'];
            }
            return sumCount(node);
        }

        private int sumCount(TrieTreNode trieTreNode) {
            if (trieTreNode == null)
                return 0;
            int sum = trieTreNode.count;
            for (int i = 0; i < 26; i++) {
                sum += sumCount(trieTreNode.children[i]);
            }
            return sum;
        }

        private int get(String word) {
            TrieTreNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    return 0;
                }
                node = node.children[word.charAt(i) - 'a'];
            }
            return node.count;
        }

        private static class TrieTreNode {
            TrieTreNode[] children = new TrieTreNode[26];
            int count;
        }
    }
}
