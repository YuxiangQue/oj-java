package com.placeholder.leetcode.linkedlist

import com.placeholder.predef.ListNode


object AddTwoNumbers extends App {

    val l1 = new ListNode(1)
    l1.next = new ListNode(2)
    l1.next.next = new ListNode(3)
    val l2 = new ListNode(9)
    l2.next = new ListNode(4)
    l2.next.next = new ListNode(7)
    val l3 = addTwoNumbers(l1, l2)
    var l4 = l3

    def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
        if (l1 == null) {
            return copyList(l2)
        }
        if (l2 == null) {
            return copyList(l1)
        }
        var p1 = l1
        var p2 = l2

        val virtual = new ListNode(0) // virtual
        var p3 = virtual
        var carry = 0
        while (p1 != null && p2 != null) {
            val sum = (p1.`val` + p2.`val` + carry) % 10
            carry = (p1.`val` + p2.`val` + carry) / 10
            p3.next = new ListNode(sum)
            p3 = p3.next
            p1 = p1.next
            p2 = p2.next
        }
        while (p1 != null) {
            val sum = (p1.`val` + carry) % 10
            carry = (p1.`val` + carry) / 10
            p3.next = new ListNode(sum)
            p3 = p3.next
            p1 = p1.next
        }
        while (p2 != null) {
            val sum = (p2.`val` + carry) % 10
            carry = (p2.`val` + carry) / 10
            p3.next = new ListNode(sum)
            p3 = p3.next
            p2 = p2.next
        }
        if (carry != 0) {
            p3.next = new ListNode(carry);
        }
        virtual.next
    }

    def copyList(list: ListNode): ListNode = {
        var p = list // virtual
        val virtual: ListNode = new ListNode(0)
        var q = virtual
        while (p != null) {
            q.next = new ListNode(p.`val`)
            p = p.next
            q = q.next
        }
        virtual.next
    }
}
