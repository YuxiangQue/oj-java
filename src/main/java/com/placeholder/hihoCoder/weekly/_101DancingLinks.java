package com.placeholder.hihoCoder.weekly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Dancing Links
 */
class _101DancingLinks {

    public static void main(String[] args) {
        DancingLinks sln = new DancingLinks();
        Scanner scn = new Scanner(System.in);
        int numOfCases = scn.nextInt();
        for (int k = 0; k < numOfCases; k++) {
            int n = scn.nextInt();
            int m = scn.nextInt();
            int[][] board = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] = scn.nextInt();
                }
            }
            sln.build(board);
            System.out.println(sln.dance() ? "Yes" : "No");
        }
        return;
    }

    static class DancingLinks {

        Node head;
        Node[] columnHead;
        List<Integer> ans;

        public void build(int[][] board) {
            int n = board.length;
            int m = board[0].length;
            head = new Node();
            head.left = head.right = head.up = head.down = head;
            head.x = head.y = -1;
            columnHead = new Node[m];
            Node prev = head;
            for (int i = 0; i < m; i++) {
                Node p = columnHead[i] = new Node();
                p.up = p.down = p;
                p.x = -1;
                p.y = i;
                p.right = prev.right;
                p.left = prev;
                prev.right.left = p;
                prev.right = p;
                prev = p;
            }
            int[][] index = new int[n][m];
            int count = 0;
            List<Node> node = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) {
                        index[i][j] = count;
                        Node tp = new Node();
                        tp.left = tp.right = tp.up = tp.down = tp;
                        tp.x = i;
                        tp.y = j;
                        node.add(tp);
                        count += 1;
                    }
                }
            }

            for (int j = 0; j < m; j++) {
                prev = columnHead[j];
                for (int i = 0; i < n; i++) {
                    if (board[i][j] == 1) {
                        Node p = node.get(index[i][j]);
                        p.down = prev.down;
                        p.up = prev;
                        prev.down.up = p;
                        prev.down = p;
                        prev = p;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                prev = null;
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) {
                        if (prev == null) {
                            prev = node.get(index[i][j]);
                        } else {
                            Node p = node.get(index[i][j]);
                            p.right = prev.right;
                            p.left = prev;
                            prev.right.left = p;
                            prev.right = p;
                            prev = p;
                        }
                    }
                }
            }
        }

        public void remove(int col) {
            Node p = columnHead[col];
            p.right.left = p.left;
            p.left.right = p.right;
            Node p2 = p.down;
            while (p2 != p) {
                Node p3 = p2.right;
                while (p3 != p2) {
                    p3.down.up = p3.up;
                    p3.up.down = p3.down;
                    p3 = p3.right;
                }
                p2 = p2.down;
            }
        }

        public void resume(int col) {
            Node p = columnHead[col];
            p.right.left = p;
            p.left.right = p;
            Node p2 = p.down;
            while (p2 != p) {
                Node p3 = p2.right;
                while (p3 != p2) {
                    p3.down.up = p3;
                    p3.up.down = p3;
                    p3 = p3.right;
                }
                p2 = p2.down;
            }
        }

        public boolean dance() {
            ans = new ArrayList<>();
            Node p = head.right;
            if (p == head) { // 若head的右边就是head自己，则已经找到解
                return true;
            }
            Node p2 = p.down;
            if (p2 == p) { // 当前列没有节点，则当前列一定不会被覆盖
                return false;
            }
            remove(p.y);  // 删除当前列
            while (p2 != p) {  // 枚举选取每一个节点
                ans.add(p2.x); // 将行压入答案栈中

                // 删除p2所在行的其他列
                Node p3 = p2.right;
                while (p3 != p2) {
                    remove(p3.y);
                    p3 = p3.right;
                }
                if (dance()) {
                    return true;
                }
                // 恢复p2所在行的其他列
                p3 = p2.left; // 这个地方需要反向来做
                while (p3 != p2) {
                    resume(p3.y);
                    p3 = p3.left;
                }
                p2 = p2.down;
            }
            resume(p.y);
            return false;
        }

        class Node {
            Node left;
            Node right;
            Node up;
            Node down;
            int x;
            int y;
        }
    }
}
