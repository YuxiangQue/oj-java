package com.placeholder.leetcode.array

/**
 * @author 阙宇翔
 * @version 2016/2/18
 */

fun _118PascalsTriangle.test() {
    println(this.generate(0))
    println(this.generate(1))
    println(this.generate(2))
    println(this.generate(3))
    println(this.generate(4))
    println(this.generate(5))
}

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun main(args: Array<String>) {
    _118PascalsTriangle().test()
}