package com.placeholder.test.ntest2015april;

import org.junit.Assert;
import org.junit.Test;

/**
 * http://hihocoder.com/contest/ntest2015april/problem/2
 * 井字棋
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
                if (chessBoard[row][col] == 'X') {
                    ++chessX[0];
                } else if (chessBoard[row][col] == 'O') {
                    ++chessO[0];
                }
            }
        }
    }

    // 判断下一步
    private static JudgeNextResult judgeNext(char[][] chessBoard, char nextStep) {
        for (int row = 0; row < 3; row++) {
            int x1 = 0;
            int x2 = 0;
            for (int col = 0; col < 3; col++) {
                if (chessBoard[row][col] == nextStep) {
                    ++x1;
                } else if (chessBoard[row][col] == '_') {
                    ++x2;
                }
            }
            if (x1 == 2 && x2 == 1) {
                return JudgeNextResult.NEXT_WIN;
            }
        }
        for (int row = 0; row < 3; row++) {
            int x1 = 0;
            int x2 = 0;
            for (int col = 0; col < 3; col++) {
                if (chessBoard[row][col] == nextStep) {
                    ++x1;
                } else if (chessBoard[row][col] == '_') {
                    ++x2;
                }
            }
            if (x1 == 2 && x2 == 1) {
                return JudgeNextResult.NEXT_WIN;
            }
        }
        int x1 = 0;
        int x2 = 0;
        for (int row = 0; row < 3; row++) {
            if (chessBoard[row][row] == nextStep) {
                ++x1;
            }
            if (chessBoard[row][row] == '_') {
                ++x2;
            }
        }
        if (x1 == 2 && x2 == 1) {
            return JudgeNextResult.NEXT_WIN;
        }
        x1 = 0;
        x2 = 0;
        for (int row = 0; row < 3; row++) {
            if (chessBoard[row][3 - row - 1] == nextStep) {
                ++x1;
            }
            if (chessBoard[row][3 - row - 1] == ' ') {
                ++x2;
            }
        }
        if (x1 == 2 && x2 == 1) {
            return JudgeNextResult.NEXT_WIN;
        }
        return JudgeNextResult.NEXT_CAN_NOT_WIN;
    }

    // 判断当前状态
    private static JudgeResult judge(char[][] chessBoard) {
        int winX = 0;
        int winO = 0;
        for (int row = 0; row < 3; row++) {
            int x = 0;
            int o = 0;
            for (int col = 0; col < 3; col++) {
                if (chessBoard[row][col] == 'X') {
                    ++x;
                } else if (chessBoard[row][col] == 'O') {
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
                if (chessBoard[row][col] == 'X') {
                    ++x;
                } else if (chessBoard[row][col] == 'O') {
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
            if (chessBoard[row][row] == 'X') {
                ++x;
            }
            if (chessBoard[row][row] == 'O') {
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
            if (chessBoard[row][3 - row - 1] == 'X') {
                ++x;
            }
            if (chessBoard[row][3 - row - 1] == 'O') {
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
            return JudgeResult.BOTH_WIN;
        } else if (winX > 0) {
            return JudgeResult.X_WIN;
        } else if (winO > 0) {
            return JudgeResult.O_WIN;
        } else {
            return JudgeResult.NO_WIN;
        }
    }

    public static String ticTacToe(char[][] chessboard) {

        int[] numOfChessX = new int[1];
        int[] numOfChessO = new int[1];
        numOfChess(chessboard, numOfChessX, numOfChessO);
        JudgeResult judgeResult = judge(chessboard);

        if (numOfChessX[0] == numOfChessO[0] + 1) { // X 先手

            if (judgeResult == JudgeResult.X_WIN) {
                return "X win";
            } else if (judgeResult == JudgeResult.BOTH_WIN || judgeResult == JudgeResult.O_WIN) {
                return "Invalid";
            } else {
                if (numOfChessO[0] + numOfChessX[0] == 9) {
                    return "Draw";
                } else {
                    JudgeNextResult judgeNextResult = judgeNext(chessboard, 'O');
                    if (judgeNextResult == JudgeNextResult.NEXT_CAN_NOT_WIN) {
                        return "Next cannot win";
                    } else {
                        return "Next win";
                    }
                }
            }
        } else if (numOfChessX[0] == numOfChessO[0]) {  // O 后手
            if (judgeResult == JudgeResult.O_WIN) {
                return "O win";
            } else if (judgeResult == JudgeResult.BOTH_WIN || judgeResult == JudgeResult.X_WIN) {
                return "Invalid";
            } else {
                if (numOfChessO[0] + numOfChessX[0] == 9) {
                    return "Draw";
                } else {
                    JudgeNextResult judgeNextResult = judgeNext(chessboard, 'X');
                    if (judgeNextResult == JudgeNextResult.NEXT_CAN_NOT_WIN) {
                        return "Next cannot win";
                    } else {
                        return "Next win";
                    }
                }
            }
        }
        return "Invalid";
    }

    @Test
    public void test() {
        Assert.assertEquals("Invalid", ticTacToe(new char[][]{{'_', '_', 'O'}, {'_', 'X', 'O'}, {'_', '_', '_'}}));
        Assert.assertEquals("X win", ticTacToe(new char[][]{{'X', 'X', 'X'}, {'_', '_', '_'}, {'O', 'O', '_'}}));
        Assert.assertEquals("Draw", ticTacToe(new char[][]{{'X', 'X', 'O'}, {'O', 'O', 'X'}, {'X', 'X', 'O'}}));
        Assert.assertEquals("Next win", ticTacToe(new char[][]{{'X', '_', 'X'}, {'O', 'O', '_'}, {'_', '_', '_'}}));
        Assert.assertEquals("Next cannot win", ticTacToe(new char[][]{{'X', 'O', '_'}, {'X', 'X', '_'}, {'_', '_', 'O'}}));
    }

    private enum JudgeNextResult {
        NEXT_WIN,
        NEXT_CAN_NOT_WIN,
    }

    private enum JudgeResult {
        X_WIN,
        O_WIN,
        BOTH_WIN,
        NO_WIN
    }
}
