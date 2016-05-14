package com.placeholder.cracking_the_code_interview

object _17_6 {

    def main(args: Array[String]) {
        makeSorted(Array(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19))
    }

    def makeSorted(arr: Array[Int]): (Int, Int) = {
        // 左子序列
        var left = 0
        while (left + 1 < arr.length && arr(left) < arr(left + 1))
            left += 1

        // 右子序列
        var right = arr.length - 1
        while (right - 1 >= 0 && arr(right) > arr(right - 1))
            right -= 1

        // 中间部分最大值和最小值
        var minIndex = left + 1
        var maxIndex = left + 1
        var index = left + 1
        while (index <= right - 1) {
            if (arr(index) < arr(minIndex)) {
                minIndex = index
            }
            if (arr(index) > arr(maxIndex)) {
                maxIndex = index
            }
            index += 1
        }

        while (left >= 0 && arr(minIndex) < arr(left)) {
            left -= 1
        }
        while (right < arr.length && arr(maxIndex) > arr(right)) {
            right += 1
        }
        (left, right)
    }
}
