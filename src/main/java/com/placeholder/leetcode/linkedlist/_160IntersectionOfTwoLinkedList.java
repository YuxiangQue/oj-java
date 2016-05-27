package com.placeholder.leetcode.linkedlist;

import com.placeholder.predef.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _160IntersectionOfTwoLinkedList {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode pA = headA;
        ListNode pB = headB;
        boolean travedA = false;
        boolean travedB = false;
        while (true) {
            if (!travedA && pA == null) {
                pA = headB;
                travedA = true;
            }
            if (!travedB && pB == null) {
                pB = headA;
                travedB = true;
            }
            if (travedA && travedB && (pA == null || pB == null))
                break;
            if (pA != pB) {
                pA = pA.next;
                pB = pB.next;
            } else {
                break;
            }
        }
        return pA;
    }
}
