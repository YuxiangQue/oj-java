package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _23MergeKSortedLists {
    ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length);
    }

    ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left >= right)
            return null;
        if (left == right - 1)
            return lists[left];
        if (left == right - 2)
            return merge2Lists(lists[left], lists[left + 1]);
        int middle = (left + right) / 2;
        ListNode leftMerged = mergeKLists(lists, left, middle);
        ListNode rightMerged = mergeKLists(lists, middle, right);
        return merge2Lists(leftMerged, rightMerged);
    }

    ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode tail = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null)
            tail.next = l1;
        else if (l2 != null)
            tail.next = l2;
        else
            tail.next = null;
        return head;
    }
}
