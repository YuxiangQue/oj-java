package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * Created by yuxiangque on 2016/3/21.
 */
public class _25ReverseNodesInKGroup {
    ListNode reverseKGroup(ListNode head, int k) {
        ListNode localTail = null;
        ListNode node = head;
        boolean compltedGroup = true;
        while (compltedGroup) {
            ListNode prevLocalTail = localTail;
            localTail = node;
            ListNode localHead = null;

            // If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is
            ListNode tmp = node;
            for (int i = 0; i < k; ++i) {
                if (tmp == null) {
                    compltedGroup = false;
                    break;
                }
                tmp = tmp.next;
            }
            if (!compltedGroup) {
                localHead = node;
            } else {
                for (int i = 0; i < k; ++i) {
                    ListNode nodeNext = node.next;
                    node.next = localHead;
                    localHead = node;
                    node = nodeNext;
                }
            }
            if (prevLocalTail != null) {
                prevLocalTail.next = localHead;
            } else {  // first group
                head = localHead;
            }
        }
        return head;
    }
}
