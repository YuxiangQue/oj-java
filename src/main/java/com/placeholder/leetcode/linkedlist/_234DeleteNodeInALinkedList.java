package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _234DeleteNodeInALinkedList {
    boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        if (head.next == null)
            return true;
        ListNode slowPrev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) { // even
                fast = fast.next;
            }
        }
        slowPrev.next = null;
        ListNode lastHalfHead = slow;
        ListNode iter = lastHalfHead.next;
        lastHalfHead.next = null;
        while (iter != null) {
            ListNode next = iter.next;
            iter.next = lastHalfHead;
            lastHalfHead = iter;
            iter = next;
        }

        //
        ListNode firstHalfNode = head;
        ListNode secondHalfNode = lastHalfHead;
        while (secondHalfNode != null) {
            if (firstHalfNode.val != secondHalfNode.val)
                return false;
            firstHalfNode = firstHalfNode.next;
            secondHalfNode = secondHalfNode.next;
        }
        return true;
    }
}
