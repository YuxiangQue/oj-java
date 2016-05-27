package com.gallery.dsl

import scala.collection.mutable.ListBuffer

/**
  * http://hihocoder.com/contest/ntest2015septdev/problem/3
  */
object CompletedExpression {


    def main(args: Array[String]) {
        val parser = new SimpleLispParser()
        println(s"${parser.parse("(+ 9)")}[9]")
        println(s"${parser.parse("(+ 1 2)")}[3]")
        println(s"${parser.parse("(+ 1 2 3 4)")}[10]")
        println(s"${parser.parse("(- 9)")}[-9]")
        println(s"${parser.parse("(- 5 4)")}[1]")
        println(s"${parser.parse("(* 2 3)")}[6]")
        println(s"${parser.parse("(* 1 2 3 4)")}[24]")

        println(s"${parser.parse("(+ 1 (* 2 3)))")}[invalid expression]")
        println(s"${parser.parse("(2 3)")}[invalid expression]")
        println(s"${parser.parse("(- 3 2 1)")}[invalid expression6]")
        println(s"${parser.parse("(+ (+ 1 2) (* 2 3) (- 2 1))")}[10]")
        println(s"${parser.parse("(- 2)")}[-2]")
    }

    trait Token

    trait Expr {
        def evaluate(): Int
    }

    case class AddToken() extends Token

    case class MinusToken() extends Token

    case class MulToken() extends Token

    case class LParenToken() extends Token

    case class RParenToken() extends Token

    case class IntToken(value: Int) extends Token

    case class EmptyToken() extends Token

    case class AddExpr(exs: ListBuffer[Expr]) extends Expr() {
        override def evaluate(): Int = {
            exs.map(ex => ex.evaluate()).sum
        }
    }

    case class MinusExpr(exs: ListBuffer[Expr]) extends Expr() {
        override def evaluate(): Int = {
            if (exs.length == 1) {
                -exs.head.evaluate()
            } else {
                exs.head.evaluate() - exs(1).evaluate()
            }
        }
    }

    case class MulExpr(exs: ListBuffer[Expr]) extends Expr() {
        override def evaluate(): Int = {
            exs.map(ex => ex.evaluate()).product
        }
    }

    case class IntExpr(value: Int) extends Expr() {
        override def evaluate(): Int = {
            value
        }
    }

    class SimpleLispParser {


        var tokens: ListBuffer[Token] = ListBuffer.empty
        var tokenIndex = 0

        // expect next to be
        def next(token: Token): Token = {
            if (hasNext) {
                if (tokens(tokenIndex) != token) {
                    throw new RuntimeException("invalid expression")
                }
                tokenIndex += 1
                token
            } else {
                throw new RuntimeException("invalid expression")
            }
        }

        def next(): Unit = {
            tokenIndex += 1
        }

        def hasNext: Boolean = {
            tokenIndex < tokens.length
        }

        def peek(): Token = {
            if (hasNext) {
                tokens(tokenIndex)
            } else {
                throw new RuntimeException("invalid expression")
            }
        }

        def expression(): Expr = {
            peek() match {
                case token1: LParenToken =>
                    next()
                    peek() match {
                        case token2: AddToken =>
                            next()
                            val exs = expressions().filter(expr => expr != null)
                            next(RParenToken())
                            if (exs.length >= 1) {
                                AddExpr(exs)
                            } else {
                                throw new RuntimeException("invalid expression")
                            }
                        case token2: MinusToken =>
                            next()
                            val exs = expressions().filter(expr => expr != null)
                            next(RParenToken())
                            if (exs.length >= 1 && exs.length <= 2) {
                                MinusExpr(exs)
                            } else {
                                throw new RuntimeException("invalid expression")
                            }
                        case token2: MulToken =>
                            next()
                            val exs = expressions().filter(expr => expr != null)
                            next(RParenToken())
                            if (exs.length >= 2) {
                                MulExpr(exs)
                            } else {
                                throw new RuntimeException("invalid expression")
                            }
                        case _ => throw new RuntimeException("invalid expression")
                    }
                case token1: RParenToken =>
                    null
                case token1: IntToken =>
                    val expr = IntExpr(token1.value)
                    next()
                    expr
                case _ =>
                    throw new RuntimeException("invalid expression")
            }
        }

        def expressions(): ListBuffer[Expr] = {
            val exs = new scala.collection.mutable.ListBuffer[Expr]
            while (hasNext) {
                val ex = expression()
                if (ex == null) {
                    return exs
                }
                exs.append(ex)
            }
            exs
        }

        def parse(input: String): String = {
            tokens = ListBuffer.empty
            tokenIndex = 0
            tokens = lexi(input)
            try {
                val result = expression().evaluate().toString
                if (hasNext) {
                    "invalid expression"
                } else {
                    result
                }
            } catch {
                case ex: Exception =>
                    "invalid expression"
            }
        }

        def lexi(input: String): ListBuffer[Token] = {
            val chars = input.toCharArray
            var index = 0

            def hasNextToken: Boolean = index < chars.length

            def nextToken(): Option[Token] = {
                val sb = new StringBuilder
                val STATE_START = 0
                val STATE_INT = 1
                var state = STATE_START
                while (hasNextToken) {
                    state match {
                        case STATE_START =>
                            if (List(' ', '\t', '\n').contains(chars(index))) {
                                index += 1
                            } else if (chars(index) == '(') {
                                index += 1
                                return Some(LParenToken())
                            } else if (chars(index) == ')') {
                                index += 1
                                return Some(RParenToken())
                            } else if (chars(index) == '+') {
                                index += 1
                                return Some(AddToken())
                            } else if (chars(index) == '-') {
                                index += 1
                                return Some(MinusToken())
                            } else if (chars(index) == '*') {
                                index += 1
                                return Some(MulToken())
                            } else if (chars(index) >= '0' && chars(index) <= '9') {
                                state = STATE_INT
                                sb.append(chars(index))
                                index += 1
                            }
                        case STATE_INT =>
                            if (chars(index) >= '0' && chars(index) <= '9') {
                                sb.append(chars(index))
                                index += 1
                            } else {
                                return Some(IntToken(sb.toString().toInt))
                            }
                    }
                }
                None
            }
            while (hasNextToken) {
                val token = nextToken()
                token match {
                    case None =>
                    case Some(token1) => tokens.append(token1)
                }
            }
            tokens
        }
    }

}




