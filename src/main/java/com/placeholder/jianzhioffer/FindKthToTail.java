package com.placeholder.jianzhioffer;

import com.placeholder.builtin.ListNode;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class FindKthToTail {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode p = head; // ahead
        ListNode q = head;
        while (k > 0) {
            if (p == null)
                return null;
            p = p.next;
            --k;
        }
        while (p != null) {
            p = p.next;
            q = q.next;
        }
        return q;
    }
}
