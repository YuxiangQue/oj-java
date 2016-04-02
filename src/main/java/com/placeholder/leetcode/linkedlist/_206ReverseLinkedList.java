package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _206ReverseLinkedList {
    ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode p = head;
        ListNode next = null;
        while (p != null) {
            next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return prev;
    }
}
