package com.placeholder.leetcode.linkedlist;

import com.placeholder.builtin.ListNode;
import org.junit.Test;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 * @see _19RemoveNthNodeFromEndOfList
 */
public class _234DeleteNodeInALinkedList {


    @Test
    public void test() {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        isPalindrome(head);
    }

    // 从中间开始分为两部分
    ListNode partition(ListNode head) {
        if (head == null)
            return null;
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
        return slow;
    }

    ListNode reverse(ListNode head) {
        if (head == null)
            return null;
        ListNode p = head;   // p is previous node of the current to be inserted before head
        while (p.next != null) {
            ListNode tmp = p.next;
            p.next = tmp.next;
            tmp.next = head;
            head = tmp;
        }
        return head;
    }

    boolean isPalindrome(ListNode head) {


        // find the half of the linked list
        if (head == null)
            return true;
        if (head.next == null)
            return true;

        // reverse the second half
        ListNode secondHead = partition(head);
        secondHead = reverse(secondHead);

        //
        while (secondHead != null) {
            if (head.val != secondHead.val)
                return false;
            head = head.next;
            secondHead = secondHead.next;
        }
        return true;
    }
}
