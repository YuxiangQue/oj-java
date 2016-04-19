package com.placeholder.leetcode.linkedlist;

import com.placeholder.builtin.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _142LinkedListCycle2 {

    /**
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        do {
            fast = fast.next;
            if (fast == null)  // no cycle
                return null;
            fast = fast.next;
            if (fast == null)  // no cycle
                return null;
            slow = slow.next;
        } while (fast != slow);
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
