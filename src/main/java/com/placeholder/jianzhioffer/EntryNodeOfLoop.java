package com.placeholder.jianzhioffer;

import com.placeholder.predef.ListNode;

/**
 * 一个链表中包含环，请找出该链表的环的入口结点。
 *
 * @author yuxiangque
 * @version 2016/4/20
 */
public class EntryNodeOfLoop {

    private static boolean hasLoop(ListNode pHead) {
        if (pHead == null)
            return false;
        ListNode fast = pHead;
        ListNode slow = pHead;
        do {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            fast = fast.next;
            if (fast == null) {
                return false;
            }
        }
        while (fast != slow);
        return true;
    }

    public static ListNode EntryNodeOfLoop(ListNode pHead) {
        if (!hasLoop(pHead))
            return null;
        ListNode fast = pHead;
        ListNode slow = pHead;
        do {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }
        while (fast != slow);

        fast = pHead;
        do {
            slow = slow.next;
            fast = fast.next;
        }
        while (fast != slow);
        return fast;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.buildList(1, 2, 3);
        head.next.next.next = head.next;
        System.out.println(EntryNodeOfLoop(head).val);
    }
}
