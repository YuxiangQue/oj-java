package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _19RemoveNthNodeFromEndOfList {
    ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        ListNode q = head;
        ListNode pprev = null;
        while (n > 0) {
            q = q.next;
            --n;
        }
        while (q != null) {
            pprev = p;
            p = p.next;
            q = q.next;
        }
        // p == head
        if (pprev == null) {
            head = head.next;
        } else {
            pprev.next = p.next;
        }
        return head;
    }
}
