package com.placeholder.jianzhioffer;

import com.placeholder.builtin.ListNode;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head; // previous node of the one to insert
        while (p.next != null) {
            ListNode temp = p.next;
            p.next = temp.next;
            temp.next = head;
            head = temp;
        }
        return head;
    }
}
