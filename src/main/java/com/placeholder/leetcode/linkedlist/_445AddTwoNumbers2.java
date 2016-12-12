package com.placeholder.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class _445AddTwoNumbers2 {
    private static ListNode copyList(ListNode l) {
        if (l == null) return null;
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l != null) {
            p.next = new ListNode(l.val);
            p = p.next;
            l = l.next;
        }
        return head.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return copyList(l2);
        if (l2 == null) return copyList(l1);

        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        ListNode head = new ListNode(0);
        int carry = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            ListNode p1 = s1.pop();
            ListNode p2 = s2.pop();

            ListNode p3 = new ListNode((carry + p1.val + p2.val) % 10);
            p3.next = head.next;
            head.next = p3;
            carry = (carry + p1.val + p2.val) / 10;
        }
        while (!s1.isEmpty()) {
            ListNode p1 = s1.pop();
            ListNode p3 = new ListNode((carry + p1.val) % 10);
            p3.next = head.next;
            head.next = p3;
            carry = (carry + p1.val) / 10;
        }
        while (!s2.isEmpty()) {
            ListNode p2 = s2.pop();
            ListNode p3 = new ListNode((carry + p2.val) % 10);
            p3.next = head.next;
            head.next = p3;
            carry = (carry + p2.val) / 10;
        }

        if (carry != 0) {
            ListNode p3 = new ListNode((carry) % 10);
            p3.next = head.next;
            head.next = p3;
        }
        return head.next;
    }

    @Test
    public void test1() {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode l3 = addTwoNumbers(l1, l2);

        Assert.assertEquals(l3.val, 7);
        Assert.assertEquals(l3.next.val, 8);
        Assert.assertEquals(l3.next.next.val, 0);
        Assert.assertEquals(l3.next.next.next.val, 7);
    }

    @Test
    public void test2() {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l3 = addTwoNumbers(l1, null);

        Assert.assertEquals(l3.val, 7);
        Assert.assertEquals(l3.next.val, 2);
        Assert.assertEquals(l3.next.next.val, 4);
        Assert.assertEquals(l3.next.next.next.val, 3);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
