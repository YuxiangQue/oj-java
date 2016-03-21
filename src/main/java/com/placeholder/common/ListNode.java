package com.placeholder.common;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */

// Definition for singly-linked list.

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode buildList(int[] vals) {
        if (vals == null || vals.length == 0)
            return null;
        ListNode head = new ListNode(vals[0]);
        ListNode p = head;
        for (int index = 1; index < vals.length; ++index) {
            p.next = new ListNode(vals[index]);
            p = p.next;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%d", this.val));
        for (ListNode p = this.next; p != null; p = p.next) {
            sb.append(String.format(", %d", p.val));
        }
        sb.append(String.format("]"));
        return sb.toString();
    }
}

