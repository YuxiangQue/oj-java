package com.placeholder.leetcode.backtracking;

import com.placeholder.common.trie.AlphaTrie;

import java.util.*;

/**
 * https://leetcode.com/problems/word-search-ii/
 * http://blog.csdn.net/ljiabin/article/details/45846527
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _212WordSearch2 {

    public static char[][] toCharCharArray(String[] strings) {
        char[][] charCharArray = new char[strings.length][strings[0].length()];
        for (int i = 0; i < strings.length; ++i) {
            charCharArray[i] = strings[i].toCharArray();
        }
        return charCharArray;
    }

    public static void main(String[] args) {
        char[][] charCharArray = toCharCharArray(new String[]{"oaan", "etae", "ihkr", "iflv"});
        System.out.println(new WordSearch2().findWords(charCharArray, new String[]{"oath", "pea", "eat", "rain"}));
    }

    static class WordSearch2 {
        AlphaTrie alphaTrie;
        char[][] board;
        int rows;
        int cols;
        Set<String> found;

        public List<String> findWords(char[][] board, String[] words) {
            rows = board.length;
            cols = board[0].length;
            this.board = board;
            found = new HashSet<>();
            alphaTrie = new AlphaTrie();
            for (String word : words) {
                alphaTrie.insert(word);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    dfs(i, j, sb);
                }
            }
            List<String> list = new ArrayList<>();
            list.addAll(found);
            return list;
        }

        private boolean dfs(
                int row,
                int col,
                StringBuilder sb) {
            if (row < 0 || row > rows - 1 || col < 0 || col > cols - 1) { // 越界
                return false;
            }
            if (board[row][col] == '#') { // visited
                return false;
            }
            char tmp = board[row][col];
            sb.append(tmp);
            String word = sb.toString();
            if (!alphaTrie.startsWith(word)) {
                sb.deleteCharAt(sb.length() - 1);
                return false;
            } else if (alphaTrie.search(word)) {
                found.add(word);
            }
            board[row][col] = '#';  // visited
            boolean result = (dfs(row - 1, col, sb)) ||
                    (dfs(row + 1, col, sb)) ||
                    (dfs(row, col - 1, sb)) ||
                    (dfs(row, col + 1, sb));
            board[row][col] = tmp;
            sb.deleteCharAt(sb.length() - 1);
            return result;
        }
    }
}
