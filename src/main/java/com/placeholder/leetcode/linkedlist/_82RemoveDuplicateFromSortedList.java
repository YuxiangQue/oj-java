package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _82RemoveDuplicateFromSortedList {
    ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pprev = null, p = head, q = null;
        boolean duplicate = false;

        while (p != null) {
            q = p.next;
            while (q != null) {
                if (q.val == p.val) {
                    p.next = q.next;
                    q = p.next;
                    duplicate = true;
                } else {
                    break;
                }
            }
            if (duplicate) {
                if (pprev == null) {
                    head = p.next;
                    p = head;
                    pprev = null;
                } else {
                    pprev.next = p.next;
                    p = pprev.next;
                }
                duplicate = false;
            } else {
                pprev = p;
                p = p.next;
            }
        }

        return head;
    }
}
