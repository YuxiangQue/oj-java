package com.placeholder.leetcode.linkedlist;

import com.placeholder.predef.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _19RemoveNthNodeFromEndOfList {
    ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode slowPrev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            fast = fast.next;
            --n;
        }
        while (fast != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        // p == head
        if (slowPrev == null) {
            head = head.next;
        } else {
            slowPrev.next = slow.next;
        }
        return head;
    }
}
