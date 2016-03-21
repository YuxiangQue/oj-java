package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _142LinkedListCycle2 {

    /**
     * https://leetcode.com/problems/linked-list-cycle-ii/
     * A和B分别为慢速和快速指针
     * 设M和N分别为公共路径的长度和环的长度
     * 相遇时，2xA-M-N = xA-M
     * 则有，xA =N
     * 此时A在圆环上的位置为，xA-M = N-M
     * 设B为起点，A和B同速启动，则相遇点为环交点
     *
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
