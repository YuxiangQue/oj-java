package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _206ReverseLinkedList {
    ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode p = head;   // p is previous node of the current to be inserted before head
        while (p.next != null) {
            ListNode tmp = p.next;
            p.next = tmp.next;
            tmp.next = head;
            head = tmp;
        }
        return head;
    }
}
