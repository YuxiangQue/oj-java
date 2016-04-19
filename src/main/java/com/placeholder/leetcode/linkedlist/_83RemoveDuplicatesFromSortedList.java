package com.placeholder.leetcode.linkedlist;

import com.placeholder.builtin.ListNode;


/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _83RemoveDuplicatesFromSortedList {
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
