package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _203RemoveLinkedListElements {
    ListNode removeElements(ListNode head, int val) {
        ListNode p = head;
        ListNode prev = head;

        while (p != null) {
            if (p.val == val) {
                if (p == head) {
                    p = head.next;
                    head = prev = p;
                } else {
                    prev.next = p.next;
                    p = prev.next;
                }
            } else {
                prev = p;
                p = p.next;
            }
        }
        return head;
    }
}
