package com.placeholder.leetcode.linkedlist;

import com.placeholder.builtin.ListNode;

/**
 * https://leetcode.com/problems/reorder-list/
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _143ReorderList {
    void reorderList(ListNode head) {
        if (head == null)
            return;
        if (head.next == null)
            return;

        // Divide list into two parts
        ListNode slowPrev = null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slowPrev = slow;
            slow = slow.next;
        }
        slowPrev.next = null;

        // Reverse the list part 2
        ListNode reversedHead = slow;
        ListNode part2Node = reversedHead.next;
        reversedHead.next = null;
        while (part2Node != null) {
            ListNode tmp = part2Node.next;
            part2Node.next = reversedHead;
            reversedHead = part2Node;
            part2Node = tmp;
        }

        // Merge the list part 2
        ListNode part1Node = head;
        part2Node = reversedHead;
        while (part1Node != null) {
            ListNode tmp1 = part1Node.next;
            if (tmp1 == null) {
                part1Node.next = part2Node;
                part1Node = null;
            } else {
                ListNode tmp2 = part2Node.next;
                part2Node.next = tmp1;
                part1Node.next = part2Node;
                part1Node = tmp1;
                part2Node = tmp2;
            }
        }
    }
}
