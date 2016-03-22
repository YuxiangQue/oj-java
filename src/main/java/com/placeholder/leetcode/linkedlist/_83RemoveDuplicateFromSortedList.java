package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _83RemoveDuplicateFromSortedList {
    ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        ListNode qprev = null;
        ListNode q = null;
        for (; p != null; p = p.next) {
            qprev = p;
            q = p.next;
            while (q != null) {
                if (q.val == p.val) {
                    qprev.next = q.next;
                    q = qprev.next;
                } else {
                    qprev = q;
                    q = q.next;
                }
            }
        }
        return head;
    }
}
