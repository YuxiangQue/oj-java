//package com.placeholder.hihoCoder.weekly;
//
//import java.util.Scanner;
//
///**
// * http://hihocoder.com/contest/hiho87/problem/1
// *
// * @author 阙宇翔
// * @version 2016/3/9
// */
//public class _87SExpression {
//
//    enum TokenType {
//        IF,
//        LET,
//        TRUE,
//        FALSE,
//        LESS,
//        GREAT,
//        EQUAL,
//        LPAREN,
//        RPAREN,
//        INT,
//        ADD,
//        MINUS,
//        MUL,
//        DIV,
//        VAR,
//    }
//
//    static class Token {
//        private TokenType type;
//        private String value;
//
//        public Token(TokenType type, String value) {
//            this.type = type;
//            this.value = value;
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//    Scanner scanner;
//
//    private Token nextToken() {
//        if (scanner.hasNextInt()) {
//            return new Token(TokenType.INT, String.valueOf(scanner.nextInt()));
//        }
//        String token = scanner.next();
//        if ("(".equals(token)) {
//            return new Token(TokenType.LESS, token);
//        }
//        if (")".equals(token)) {
//            return new Token(TokenType.RPAREN, token);
//        }
//        // (f x ..)
//        if ("+".equals(token)) {
//            return new Token(TokenType.ADD, token);
//        }
//        if ("-".equals(token)) {
//            return new Token(TokenType.MINUS, token);
//        }
//        if ("*".equals(token)) {
//            return new Token(TokenType.MUL, token);
//        }
//        if ("/".equals(token)) {
//            return new Token(TokenType.DIV, token);
//        }
//        // if a b c
//        if ("if".equals(token)) {
//            return new Token(TokenType.IF, token);
//        }
//        if ("true".equals(token)) {
//            return new Token(TokenType.TRUE, token);
//        }
//        if ("false".equals(token)) {
//            return new Token(TokenType.FALSE, token);
//        }
//        if (">".equals(token)) {
//            return new Token(TokenType.GREAT, token);
//        }
//        if ("<".equals(token)) {
//            return new Token(TokenType.LESS, token);
//        }
//        if ("=".equals(token)) {
//            return new Token(TokenType.EQUAL, token);
//        }
//        if ("let".equals(token)) {
//            return new Token(TokenType.LET, token);
//        }
//        return new Token(TokenType.VAR, token);
//    }
//
//
//    void expectToken(TokenType tokenType) {
//
//    }
//
//    void consumeToken(TokenType tokenType) {
//    }
//
//    int Int_Constant_Expr() {
//        Token next = nextToken();
//        if (next.type == TokenType.LPAREN) {
//            int intValue = 0;
//            consumeToken(TokenType.LPAREN);
//            switch (nextToken().type) {
//                case ADD:
//                    intValue = Int_Constant_Expr() + Int_Constant_Expr();
//                    break;
//                case MINUS:
//                    intValue = Int_Constant_Expr() - Int_Constant_Expr();
//                    break;
//                case MUL:
//                    intValue = Int_Constant_Expr() * Int_Constant_Expr();
//                    break;
//                case DIV:
//                    intValue = Int_Constant_Expr() / Int_Constant_Expr();
//                    break;
//            }
//            consumeToken(TokenType.RPAREN);
//            return intValue;
//        } else {
//            return Integer.parseInt(next.value);
//        }
//    }
//
//    // boolean const or boolean expression
//    boolean Bool_Constant_Expr() {
//        Token next = nextToken();
//        if (next.type == TokenType.LPAREN) {
//            consumeToken(TokenType.LPAREN);
//            boolean boolValue = false;
//            switch (nextToken().type) {
//                case EQUAL:
//                    boolValue = Int_Constant_Expr() == Int_Constant_Expr();
//                    break;
//                case LESS:
//                    boolValue = Int_Constant_Expr() < Int_Constant_Expr();
//                    break;
//                case GREAT:
//                    boolValue = Int_Constant_Expr() > Int_Constant_Expr();
//                    break;
//            }
//            consumeToken(TokenType.RPAREN);
//            return boolValue;
//        } else if (next.type == TokenType.TRUE) {
//            return true;
//        } else if (next.type == TokenType.FALSE) {
//            return false;
//        } else {
//            throw new RuntimeException("");
//        }
//    }
//
//
//    Object SExpr() {
//
//    }
//
//    Object If_Epxr() {
//        consumeToken(TokenType.LPAREN);
//        consumeToken(TokenType.IF);
//        boolean test = Bool_Constant_Expr();
//        Object a = SExpr();
//        Object b = SExpr();
//        consumeToken(TokenType.RPAREN);
//        return test ? a : b;
//    }
//}
