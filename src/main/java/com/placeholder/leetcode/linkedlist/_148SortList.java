package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * Created by yuxiangque on 2016/3/21.
 */
public class _148SortList {
    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode head = null, p;
        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        p = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = p.next;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = p.next;
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            p.next = l1;
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            p.next = l2;
            p = p.next;
            l2 = l2.next;
        }
        return head;
    }

    // Sort list, [begin, end)
    ListNode innerSortList(ListNode begin, ListNode end) {
        if (begin == null)
            return null;
        if (begin == end)  // no node
            return null;
        if (begin.next == end) {  // single node
            begin.next = null;
            return begin;
        }
        // p
        ListNode fast = begin, slow = begin, slowPrev = null;
        while (fast != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
        }
        slowPrev.next = null;
        ListNode left = innerSortList(begin, slow);
        ListNode right = innerSortList(slow, end);
        ListNode merged = mergeTwoLists(left, right);
        return merged;
    }


    ListNode sortList(ListNode head) {
        return innerSortList(head, null);
    }
}
