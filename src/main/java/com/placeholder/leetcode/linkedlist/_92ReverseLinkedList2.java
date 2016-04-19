package com.placeholder.leetcode.linkedlist;

import com.placeholder.builtin.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _92ReverseLinkedList2 {
    ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prev = null;
        ListNode p = head;
        ListNode pmprev = null;
        ListNode pm = null;
        int i = 0;
        while (p != null) {
            ++i;
            if (i > m && i <= n) {
                ListNode next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            } else {
                if (i == m) {
                    pmprev = prev; // 记录开始位置
                    pm = p;
                }
                if (i == n + 1) {
                    break;
                }
                prev = p;
                p = p.next;
            }
        }
        if (pmprev == null) {
            pm.next = p;
            head = prev;
            return head;
        }
        pm.next = p;
        pmprev.next = prev;
        return head;
    }
}
