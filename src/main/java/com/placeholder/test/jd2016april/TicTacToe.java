package com.placeholder.test.jd2016april;

import org.junit.Assert;
import org.junit.Test;

/**
 * 京东，井字棋
 *
 * @author yuxiangque
 * @version 2016/4/9
 */
public class TicTacToe {

    private static void numOfChess(char[][] chessBoard, int[] chessX, int[] chessO) {
        chessX[0] = 0;
        chessO[0] = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (chessBoard[row][col] == 'x') {
                    ++chessX[0];
                } else if (chessBoard[row][col] == 'o') {
                    ++chessO[0];
                }
            }
        }
    }

    private static JudgeResult judge(char[][] chessBoard) {
        int winX = 0;
        int winO = 0;
        for (int row = 0; row < 3; row++) {
            int x = 0;
            int o = 0;
            for (int col = 0; col < 3; col++) {
                if (chessBoard[row][col] == 'x') {
                    ++x;
                } else if (chessBoard[row][col] == 'o') {
                    ++o;
                }
            }
            if (x == 3) {
                ++winX;
            }
            if (o == 3) {
                ++winO;
            }
        }
        for (int row = 0; row < 3; row++) {
            int x = 0;
            int o = 0;
            for (int col = 0; col < 3; col++) {
                if (chessBoard[row][col] == 'x') {
                    ++x;
                } else if (chessBoard[row][col] == 'o') {
                    ++o;
                }
            }
            if (x == 3) {
                ++winX;
            }
            if (o == 3) {
                ++winO;
            }
        }
        int x = 0;
        int o = 0;
        for (int row = 0; row < 3; row++) {
            if (chessBoard[row][row] == 'x') {
                ++x;
            }
            if (chessBoard[row][row] == 'o') {
                ++o;
            }
        }
        if (x == 3) {
            ++winX;
        }
        if (o == 3) {
            ++winO;
        }
        x = 0;
        o = 0;
        for (int row = 0; row < 3; row++) {
            if (chessBoard[row][3 - row - 1] == 'x') {
                ++x;
            }
            if (chessBoard[row][3 - row - 1] == 'o') {
                ++o;
            }
        }
        if (x == 3) {
            ++winX;
        }
        if (o == 3) {
            ++winO;
        }
        if (winX > 0 && winO > 0) {
            return JudgeResult.DRAW;
        } else if (winX > 0) {
            return JudgeResult.X_WIN;
        } else if (winO > 0) {
            return JudgeResult.O_WIN;
        } else {
            return JudgeResult.CONTINUE;
        }
    }

    public static String ticTacToe(char[][] chessboard) {

        int[] numOfChessX = new int[1];
        int[] numOfChessO = new int[1];
        numOfChess(chessboard, numOfChessX, numOfChessO);

        JudgeResult judgeResult = judge(chessboard);
        if (numOfChessX[0] == numOfChessO[0] + 1) { // x 先手
            if (judgeResult == JudgeResult.X_WIN) {
                return "1 Won";
            } else if (judgeResult == JudgeResult.O_WIN) {
                return "2 Won";
            } else if (judgeResult == JudgeResult.DRAW) {
                return "Draw";
            } else {
                return "2";
            }
        } else if (numOfChessO[0] == numOfChessX[0] + 1) { // o 先手
            if (judgeResult == JudgeResult.O_WIN) {
                return "1 Won";
            } else if (judgeResult == JudgeResult.X_WIN) {
                return "2 Won";
            } else if (judgeResult == JudgeResult.DRAW) {
                return "Draw";
            } else {
                return "2";
            }
        } else if (numOfChessX[0] == numOfChessO[0]) {  // x 或 o 后手
            if (judgeResult == JudgeResult.O_WIN) {
                return "2 Won";
            } else if (judgeResult == JudgeResult.X_WIN) {
                return "2 Won";
            } else if (judgeResult == JudgeResult.DRAW) {
                return "Draw";
            } else if (judgeResult == JudgeResult.CONTINUE) {
                return "1";
            }
        }
        // 非法
        return "x";
    }

    @Test
    public void test() {
        Assert.assertEquals("1", ticTacToe(new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("2", ticTacToe(new char[][]{{'x', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("2", ticTacToe(new char[][]{{'o', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("x", ticTacToe(new char[][]{{'x', 'x', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("x", ticTacToe(new char[][]{{'o', 'o', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("2", ticTacToe(new char[][]{{'x', 'x', ' '}, {'o', ' ', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("1", ticTacToe(new char[][]{{'x', 'x', ' '}, {'o', 'o', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("1 Won", ticTacToe(new char[][]{{'x', 'x', 'x'}, {'o', 'o', ' '}, {' ', ' ', ' '}}));
        Assert.assertEquals("Draw", ticTacToe(new char[][]{{'x', 'x', 'x'}, {'o', 'o', 'o'}, {' ', ' ', ' '}}));
    }

    enum JudgeResult {
        X_WIN,
        O_WIN,
        DRAW,
        CONTINUE
    }
}
