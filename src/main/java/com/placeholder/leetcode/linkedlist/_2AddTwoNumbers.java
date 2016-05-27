package com.placeholder.leetcode.linkedlist;

import com.placeholder.predef.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _2AddTwoNumbers {
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;

        if (l1 == null) {
            ListNode head = new ListNode(l2.val);
            ListNode tail = head;
            l2 = l2.next;
            while (l2 != null) {
                tail.next = new ListNode(l2.val);
                tail = tail.next;
                l2 = l2.next;
            }
            return head;
        }

        if (l2 == null) {
            ListNode head = new ListNode(l1.val);
            ListNode tail = head;
            l1 = l1.next;
            while (l1 != null) {
                tail.next = new ListNode(l1.val);
                tail = tail.next;
                l1 = l1.next;
            }
            return head;
        }

        ListNode head = new ListNode(l1.val + l2.val);
        ListNode tail = head;
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null && l2 != null) {
            int carry = tail.val / 10;
            tail.val = tail.val % 10;
            tail.next = new ListNode(carry + l1.val + l2.val);
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int carry = tail.val / 10;
            tail.val = tail.val % 10;
            tail.next = new ListNode(carry + l1.val);
            tail = tail.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int carry = tail.val / 10;
            tail.val = tail.val % 10;
            tail.next = new ListNode(carry + l2.val);
            tail = tail.next;
            l2 = l2.next;
        }

        int carry = tail.val / 10;
        tail.val = tail.val % 10;
        if (carry != 0)
            tail.next = new ListNode(carry);
        tail = tail.next;
        return head;

    }
}
