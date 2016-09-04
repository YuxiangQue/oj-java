package com.placeholder.hihoCoder.weekly;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Sudoku
 * 729行,324列的01精确覆盖问题
 */
public class _102Sudoku {


    Node head;
    Node[] colHead;
    int[] headCount;

    public static void main(String[] args) {

        _102Sudoku sln = new _102Sudoku();

        Scanner scanner = null;
        if (true)
            scanner = new Scanner(System.in);
        else
            scanner = new Scanner(new StringReader("1\n" +
                    "4 0 0 0 7 0 1 0 0\n" +
                    "0 0 1 9 0 4 6 0 5\n" +
                    "0 0 0 0 0 1 0 0 0\n" +
                    "0 0 0 7 0 0 0 0 2\n" +
                    "0 0 2 0 3 0 0 0 0\n" +
                    "8 4 7 0 0 6 0 0 0\n" +
                    "0 1 4 0 0 0 8 0 6\n" +
                    "0 2 0 0 0 0 3 0 0\n" +
                    "6 0 0 0 9 0 0 0 0"));
        int numOfCases = scanner.nextInt();

        for (int k = 0; k < numOfCases; k++) {
            int[][] board = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }

            int[][] matrix = sln.create(board);
            sln.build(matrix);
            int[][] ans = new int[9][9];

            if (sln.dance(ans)) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print((ans[i][j] + 1) + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("");
            }
        }
    }

    public void build(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        head = new Node();
        head.left = head.right = head.up = head.down = head;
        head.x = head.y = -1;
        colHead = new Node[m];
        headCount = new int[m];
        Node prev = head;
        for (int i = 0; i < m; i++) {
            Node p = colHead[i] = new Node();
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
            prev = colHead[j];
            for (int i = 0; i < n; i++) {
                if (board[i][j] == 1) {
                    Node p = node.get(index[i][j]);
                    p.down = prev.down;
                    p.up = prev;
                    prev.down.up = p;
                    prev.down = p;
                    prev = p;
                    headCount[j] += 1; // 来记录每一列除了头结点外的节点个数
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

    public void removeCol(int col) {
        Node p = colHead[col];
        p.right.left = p.left;
        p.left.right = p.right;
        Node p2 = p.down;
        while (p2 != p) {
            Node p3 = p2.right;
            while (p3 != p2) {
                headCount[p3.y] -= 1;
                p3.down.up = p3.up;
                p3.up.down = p3.down;
                p3 = p3.right;
            }
            p2 = p2.down;
        }

    }

    public void recoverCol(int col) {
        Node p = colHead[col];
        p.right.left = p;
        p.left.right = p;
        Node p2 = p.down;
        while (p2 != p) {
            Node p3 = p2.right;
            while (p3 != p2) {
                headCount[p3.y] += 1;
                p3.down.up = p3;
                p3.up.down = p3;
                p3 = p3.right;
            }
            p2 = p2.down;
        }
    }

    public boolean dance(int[][] ans) {
        if (head.right == head) { // 若head的右边就是head自己，则已经找到解
            return true;
        }
        Node p = colHead[minHeadIndex()];
        Node p2 = p.down;
        if (p2 == p) { // 当前列没有节点，则当前列一定不会被覆盖
            return false;
        }
        removeCol(p.y);  // 删除当前列
        while (p2 != p) {  // 枚举选取每一个节点

            ans[p2.x / 81][(p2.x / 9) % 9] = p2.x % 9;

            // 删除p2所在行的其他列
            Node p3 = p2.right;
            while (p3 != p2) {
                removeCol(p3.y);
                p3 = p3.right;
            }
            if (dance(ans)) {
                return true;
            }
            // 恢复p2所在行的其他列
            p3 = p2.left; // 这个地方需要反向来做
            while (p3 != p2) {
                recoverCol(p3.y);
                p3 = p3.left;
            }
            p2 = p2.down;
        }
        recoverCol(p.y);
        return false;
    }

    public int minHeadIndex() {
        int min = Integer.MAX_VALUE;
        int index = 1;
        Node p = head.right;
        while (p != head) {
            if (headCount[p.y] < min) {
                min = headCount[p.y];
                index = p.y;
            }
            p = p.right;
        }
        return index;
    }


    void set(int[][] matrix, int i, int j, int k) {
        int pid = i * 9 * 9 + j * 9 + k;

        // 第i行存在数字k
        matrix[pid][i * 9 + k] = 1;
        // 第j列存在数字k
        matrix[pid][81 + j * 9 + k] = 1;
        // 第t个九宫存在数字k
        int t = i / 3 * 3 + j / 3;
        matrix[pid][162 + t * 9 + k] = 1;
        // 第i行第j列填写有数字
        matrix[pid][243 + i * 9 + j] = 1;
    }

    int[][] create(int[][] board) {
        int[][] matrix = new int[729][324];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int k = 0; k <= 8; k++) {
                        set(matrix, i, j, k);
                    }
                } else {
                    set(matrix, i, j, board[i][j] - 1);
                }
            }
        }
        return matrix;
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
