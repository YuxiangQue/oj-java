package com.placeholder.hihoCoder.weekly

/**
  * http://hihocoder.com/contest/hiho98/problem/1
  *
  */
object _98Challenge24Solver {

    def makeNumber(numCandidates: Array[Int], used: Array[Boolean], depth: Int, nums: Array[Int]): Boolean = {
        if (depth >= 4) {
            return makeOperator(nums, 0, new Array[String](3))
        }
        (0 until 4).foreach(index => {
            if (!used(index)) {
                used(index) = true
                nums(depth) = numCandidates(index)
                if (makeNumber(numCandidates, used, depth + 1, nums)) {
                    return true
                }
                used(index) = false
            }
        })
        false
    }

    def makeOperator(nums: Array[Int], depth: Int, ops: Array[String]): Boolean = {
        if (depth >= 3) {
            if (calcType1(ops, nums) == 24.0f) {
                return true
            }
            if (calcType2(ops, nums) == 24.0f) {
                return true
            }
            return false
        }
        val opCandidates = Array("+", "-", "*", "/", "-'", "/'")
        opCandidates.foreach(op => {
            ops(depth) = op
            if (makeOperator(nums, depth + 1, ops)) {
                return true
            }
        })
        false
    }

    def operate(op: String, num1: Float, num2: Float): Float = {
        var result = 0.0f
        op match {
            case "+" =>
                result = num1 + num2
            case "-" =>
                result = num1 - num2
            case "*" =>
                result = num1 * num2
            case "/" =>
                result = num1 / num2
            case "-'" =>
                result = -num1 + num2
            case "/'" =>
                result = 1.0f / num1 * num2
        }
        result
    }

    // (((a ⊙ b) ⊙ c ) ⊙ d)
    def calcType1(ops: Array[String], nums: Array[Int]): Float = {
        var result = operate(ops(0), nums(0), nums(1))
        result = operate(ops(1), result, nums(2))
        result = operate(ops(2), result, nums(3))
        result
    }

    // ((a ⊙ b) ⊙ (c ⊙ d))
    def calcType2(ops: Array[String], nums: Array[Int]): Float = {
        val result = operate(ops(1), operate(ops(0), nums(0), nums(1)), operate(ops(2), nums(2), nums(3)))
        result
    }

    def solve(nums: Array[Int]): Boolean = makeNumber(nums, new Array(4), 0, new Array(4))

    def main(args: Array[String]) {
        println(solve(Array(5, 5, 5, 1))) // Yes
        println(solve(Array(9, 9, 9, 9))) // No
        println(solve(Array(3, 3, 8, 8))) // Yes
        // (8 / 3 -' 3) /' 8
    }
}
