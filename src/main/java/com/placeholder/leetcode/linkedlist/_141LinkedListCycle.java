package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _141LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        do {
            fast = fast.next;
            if (fast == null)
                return false;
            fast = fast.next;
            if (fast == null)
                return false;
            slow = slow.next;
        } while (slow != fast);
        return true;
    }
}
