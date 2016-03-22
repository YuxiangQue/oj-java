package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * https://leetcode.com/problems/partition-list/
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class _86PartitionList {
    ListNode partition(ListNode head, int x) {
        ListNode lastLessThan = null;
        ListNode nodePrev = null;
        ListNode node = head;
        while (node != null) {
            if (nodePrev == null) { // head
                if (node.val < x) {
                    lastLessThan = node;
                }
                nodePrev = node;
                node = node.next;
            } else {
                if (node.val < x) {
                    if (lastLessThan == null) {
                        nodePrev.next = node.next;
                        lastLessThan = node;
                        lastLessThan.next = head;
                        head = lastLessThan;
                        node = nodePrev.next;
                    } else {
                        if (nodePrev == lastLessThan) {
                            lastLessThan = node;
                            nodePrev = node;
                            node = node.next;
                        } else {
                            nodePrev.next = node.next;
                            node.next = lastLessThan.next;
                            lastLessThan.next = node;
                            lastLessThan = lastLessThan.next;
                            node = nodePrev.next;
                        }
                    }
                } else {
                    nodePrev = node;
                    node = node.next;
                }
            }
        }
        return head;
    }
}
