package com.placeholder.cracking_the_code_interview

object _17_13 {

    def convert[T](root: TreeNode[T]): (LinkedNode[T], LinkedNode[T]) = {
        if (root == null) {
            return (null, null)
        }
        val (leftHead, leftTail) = convert[T](root.left)
        val (rightHead, rightTail) = convert[T](root.right)
        if (leftHead == null && rightHead == null) {
            val current = new LinkedNode[T](root.value)
            (current, current)
        } else if (leftHead == null) {
            val current = new LinkedNode[T](root.value)
            current.next = rightHead
            rightHead.prev = current
            (current, rightTail)
        } else if (rightHead == null) {
            val current = new LinkedNode[T](root.value)
            leftTail.next = current
            current.prev = leftTail
            (leftHead, current)
        } else {
            val current = new LinkedNode[T](root.value)
            leftTail.next = current
            current.prev = leftTail
            current.next = rightHead
            rightHead.prev = current
            (leftHead, rightTail)
        }
    }

    def main(args: Array[String]) {
        val root = new TreeNode(4)
        root.left = new TreeNode(2)
        root.right = new TreeNode(5)
        root.left.left = new TreeNode(1)
        root.left.right = new TreeNode(3)
        root.left.left.left = new TreeNode(0)
        root.right = new TreeNode(5)
        root.right.right = new TreeNode(6)
        val (head, tail) = convert(root)
        return
    }

    class TreeNode[T](var value: T) {
        var left: TreeNode[T] = null
        var right: TreeNode[T] = null
    }

    class LinkedNode[T](var value: T) {
        var prev: LinkedNode[T] = null
        var next: LinkedNode[T] = null
    }

}
