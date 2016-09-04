package com.placeholder.hihoCoder.weekly;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho87/problem/1
 *
 * @author 阙宇翔
 * @version 2016/3/9
 */
public class _87SExpression {

    static Object phrase(String expr) {
        try {
            Lexer lexer = new Lexer(new Scanner(new StringReader(expr)));
            Parser parser = new Parser();
            Parser.SExpr sexpr = parser.parse(lexer.getTokens());
            Evaluator evaluator = new Evaluator();
            return evaluator.evaluate(sexpr);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static void main(String[] args) {

//        System.out.println(phrase("( + ( - 3 2 ) ( * 4 5 ) )")); // 21
//        System.out.println(phrase("( let ( x 4 ) ( if true x y ) )")); // 4
//        System.out.println(phrase("( let ( x 1 ) ( let ( x 2 ) x ) )")); // 2
//
//        System.out.println(phrase("( + ( = 1 2 ) 3 )")); // 2
//        System.out.println(phrase("( / 3 0 )")); // 2

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int k = 0; k < n; ++k) {
            System.out.println(phrase(scanner.nextLine()));
        }
        return;
    }

    static class Lexer {
        Scanner scanner;

        public Lexer(Scanner scanner) {
            this.scanner = scanner;
        }

        public List<Token> getTokens() {
            List<Token> tokens = new ArrayList<>();
            while (true) {
                Token token = nextToken();
                if (token == null)
                    break;
                tokens.add(token);
            }
            return tokens;
        }

        private Token nextToken() {
            if (!scanner.hasNext())
                return null;
            if (scanner.hasNextInt()) {
                return new Token(TokenType.INT, String.valueOf(scanner.nextInt()));
            }
            String token = scanner.next();
            if ("(".equals(token)) {
                return new Token(TokenType.LPAREN, token);
            }
            if (")".equals(token)) {
                return new Token(TokenType.RPAREN, token);
            }
            // (f x ..)
            if ("+".equals(token)) {
                return new Token(TokenType.PLUS, token);
            }
            if ("-".equals(token)) {
                return new Token(TokenType.MINUS, token);
            }
            if ("*".equals(token)) {
                return new Token(TokenType.MUL, token);
            }
            if ("/".equals(token)) {
                return new Token(TokenType.DIV, token);
            }
            // if a b c
            if ("if".equals(token)) {
                return new Token(TokenType.IF, token);
            }
            if ("true".equals(token)) {
                return new Token(TokenType.TRUE, token);
            }
            if ("false".equals(token)) {
                return new Token(TokenType.FALSE, token);
            }
            if (">".equals(token)) {
                return new Token(TokenType.GREAT, token);
            }
            if ("<".equals(token)) {
                return new Token(TokenType.LESS, token);
            }
            if ("=".equals(token)) {
                return new Token(TokenType.EQUAL, token);
            }
            if ("let".equals(token)) {
                return new Token(TokenType.LET, token);
            }
            return new Token(TokenType.VAR, token);
        }

        enum TokenType {
            IF,
            LET,
            TRUE,
            FALSE,
            LESS,
            GREAT,
            EQUAL,
            LPAREN,
            RPAREN,
            INT,
            PLUS,
            MINUS,
            MUL,
            DIV,
            VAR,
        }

        static class Token {
            private TokenType type;
            private String value;

            public Token(TokenType type, String value) {
                this.type = type;
                this.value = value;
            }
        }
    }

    static class Parser {
        private List<Lexer.Token> tokens;
        private int index;

        public Parser() {
        }

        public SExpr parse(List<Lexer.Token> tokens) {
            this.tokens = tokens;
            this.index = 0;
            return sExpr();
        }

        Lexer.Token pop(Lexer.TokenType tokenType) {
            if (tokens.get(index).type == tokenType) {
                return tokens.get(index++);
            }
            throw new RuntimeException("Unexpected token");
        }

        Lexer.Token peek(int k) {
            return tokens.get(index + k);
        }

        SExpr sExpr() {
            if (peek(0).type == Lexer.TokenType.LPAREN) {
                Lexer.Token t1 = peek(1);
                switch (t1.type) {
                    case PLUS:
                    case MINUS:
                    case MUL:
                    case DIV:
                        return arithExpr();
                    case EQUAL:
                    case LESS:
                    case GREAT:
                        return boolExpr();
                    case LET:
                        return letExpr();
                    case IF:
                        return ifExpr();
                    default:
                        throw new RuntimeException("");
                }

            } else if (peek(0).type == Lexer.TokenType.INT) {
                return new IntConstExpr(Integer.parseInt(pop(Lexer.TokenType.INT).value));
            } else if (peek(0).type == Lexer.TokenType.TRUE) {
                pop(Lexer.TokenType.TRUE);
                return new BoolConstExpr(true);
            } else if (peek(0).type == Lexer.TokenType.FALSE) {
                pop(Lexer.TokenType.FALSE);
                return new BoolConstExpr(false);
            } else if (peek(0).type == Lexer.TokenType.VAR) {
                return new VarExpr(pop(Lexer.TokenType.VAR).value);
            }
            throw new RuntimeException("");
        }

        ArithmExpr arithExpr() {
            ArithmExpr arithSExpr = null;
            pop(Lexer.TokenType.LPAREN);
            switch (peek(0).type) {
                case PLUS:
                    pop(Lexer.TokenType.PLUS);
                    arithSExpr = new ArithmExpr(sExpr(), sExpr(), ArithmExpr.Operator.PLUS);
                    break;
                case MINUS:
                    pop(Lexer.TokenType.MINUS);
                    arithSExpr = new ArithmExpr(sExpr(), sExpr(), ArithmExpr.Operator.MINUS);
                    break;
                case MUL:
                    pop(Lexer.TokenType.MUL);
                    arithSExpr = new ArithmExpr(sExpr(), sExpr(), ArithmExpr.Operator.MUL);
                    break;
                case DIV:
                    pop(Lexer.TokenType.DIV);
                    arithSExpr = new ArithmExpr(sExpr(), sExpr(), ArithmExpr.Operator.DIV);
                    break;
            }
            pop(Lexer.TokenType.RPAREN);
            return arithSExpr;
        }

        RelationExpr boolExpr() {
            RelationExpr relationExpr = null;
            pop(Lexer.TokenType.LPAREN);
            switch (peek(0).type) {
                case EQUAL:
                    pop(Lexer.TokenType.EQUAL);
                    relationExpr = new RelationExpr(sExpr(), sExpr(), RelationExpr.Operator.Equal);
                    break;
                case LESS:
                    pop(Lexer.TokenType.LESS);
                    relationExpr = new RelationExpr(sExpr(), sExpr(), RelationExpr.Operator.Less);
                    break;
                case GREAT:
                    pop(Lexer.TokenType.GREAT);
                    relationExpr = new RelationExpr(sExpr(), sExpr(), RelationExpr.Operator.Greater);
                    break;
            }
            pop(Lexer.TokenType.RPAREN);
            return relationExpr;
        }

        IfExpr ifExpr() {
            pop(Lexer.TokenType.LPAREN);
            pop(Lexer.TokenType.IF);
            IfExpr ifExpr = new IfExpr(sExpr(), sExpr(), sExpr());
            pop(Lexer.TokenType.RPAREN);
            return ifExpr;
        }

        LetExpr letExpr() {
            pop(Lexer.TokenType.LPAREN);
            pop(Lexer.TokenType.LET);

            pop(Lexer.TokenType.LPAREN);
            String varExpr = pop(Lexer.TokenType.VAR).value;
            SExpr a = sExpr();
            pop(Lexer.TokenType.RPAREN);
            SExpr b = sExpr();

            LetExpr ifExpr = new LetExpr(varExpr, a, b);
            pop(Lexer.TokenType.RPAREN);
            return ifExpr;
        }

        interface SExpr {

        }

        static class IntConstExpr implements SExpr {
            int val;

            public IntConstExpr(int val) {
                this.val = val;
            }
        }

        static class BoolConstExpr implements SExpr {
            boolean val;

            public BoolConstExpr(boolean val) {
                this.val = val;
            }
        }

        static class VarExpr implements SExpr {
            String varName;

            public VarExpr(String varName) {
                this.varName = varName;
            }
        }

        static class IfExpr implements SExpr {

            private final SExpr a, b, c;

            public IfExpr(SExpr a, SExpr b, SExpr c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }

        static class LetExpr implements SExpr {

            private final String varName;
            private final SExpr a, b;

            public LetExpr(String varName, SExpr a, SExpr b) {
                this.a = a;
                this.b = b;
                this.varName = varName;
            }
        }

        static class RelationExpr implements SExpr {

            public final SExpr x, y;
            public final Operator op;

            public RelationExpr(SExpr x, SExpr y, Operator operator) {
                this.x = x;
                this.y = y;
                this.op = operator;
            }

            enum Operator {Equal, Less, Greater}
        }

        static class ArithmExpr implements SExpr {

            public final SExpr x, y;
            public Operator op;

            public ArithmExpr(SExpr x, SExpr y, Operator op) {
                this.x = x;
                this.y = y;
                this.op = op;
            }

            enum Operator {PLUS, MINUS, MUL, DIV}
        }
    }

    static class Evaluator {
        Parser.SExpr root;

        public Evaluator() {
        }

        public Object evaluate(Parser.SExpr expr) {
            return evaluate(null, expr);
        }

        private Object evaluate(Context parentContext, Parser.SExpr expr) {
            Context context = new Context();
            context.parent = parentContext;
            if (expr instanceof Parser.ArithmExpr) {
                Parser.ArithmExpr cast = (Parser.ArithmExpr) expr;
                int x = 0, y = 0;
                try {
                    x = (int) evaluate(context, cast.x);
                    y = (int) evaluate(context, cast.y);
                } catch (ClassCastException ex) {
                    throw new RuntimeException("Type Mismatch");
                }
                try {
                    switch (cast.op) {
                        case PLUS:
                            return x + y;
                        case MINUS:
                            return x - y;
                        case MUL:
                            return x * y;
                        case DIV:
                            return x / y;
                    }
                } catch (ArithmeticException ex) {
                    throw new RuntimeException("Division By Zero");
                }

            } else if (expr instanceof Parser.RelationExpr) {
                Parser.RelationExpr cast = (Parser.RelationExpr) expr;
                int x = 0, y = 0;
                try {
                    x = (int) evaluate(context, cast.x);
                    y = (int) evaluate(context, cast.y);
                } catch (ClassCastException ex) {
                    throw new RuntimeException("Type Mismatch");
                }
                switch (cast.op) {
                    case Equal:
                        return x == y;
                    case Less:
                        return x < y;
                    case Greater:
                        return x > y;
                }
            } else if (expr instanceof Parser.IntConstExpr) {
                Parser.IntConstExpr cast = (Parser.IntConstExpr) expr;
                return cast.val;
            } else if (expr instanceof Parser.BoolConstExpr) {
                Parser.BoolConstExpr cast = (Parser.BoolConstExpr) expr;
                return cast.val;
            } else if (expr instanceof Parser.IfExpr) {
                Parser.IfExpr cast = (Parser.IfExpr) expr;
                boolean relation = (boolean) evaluate(context, cast.a);
                return relation ? evaluate(context, cast.b) : evaluate(context, cast.c);
            } else if (expr instanceof Parser.LetExpr) {
                Parser.LetExpr cast = (Parser.LetExpr) expr;
                context.variableTable.put(cast.varName, evaluate(context, cast.a));
                return evaluate(context, cast.b);
            } else { // expr instanceof Parser.VarExpr
                Parser.VarExpr cast = (Parser.VarExpr) expr;
                Object varValue = null;
                while (context != null) {
                    if (context.variableTable.containsKey(cast.varName)) {
                        varValue = context.variableTable.get(cast.varName);
                        break;
                    }
                    context = context.parent;
                }
                if (varValue == null) {
                    throw new RuntimeException("Unbound Identifier");
                }
                return varValue;
            }
            throw new RuntimeException("Unexpected");
        }

        static class Context {
            HashMap<String, Object> variableTable = new HashMap<>();
            Context parent;
        }
    }
}
