package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _61RotateList {
    ListNode rotateRight(ListNode head, int k) {
        if (k < 0 || head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }
        ListNode pprev = null;
        ListNode qprev = null;
        ListNode p = head;
        ListNode q = head;

        while (k > 0) {
            qprev = q;
            q = q.next;
            --k;
            if (q == null && k > 0) {
                q = head;
            }
        }
        while (q != null) {
            qprev = q;
            pprev = p;
            p = p.next;
            q = q.next;
        }
        if (pprev == null) // p == head
        {
            return head;
        } else {
            qprev.next = head;
            pprev.next = null;
            head = p;
        }
        return head;
    }
}
