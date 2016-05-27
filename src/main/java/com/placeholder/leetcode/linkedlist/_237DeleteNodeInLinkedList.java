package com.placeholder.leetcode.linkedlist;

import com.placeholder.predef.ListNode;

/**
 *
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _237DeleteNodeInLinkedList {
    void deleteNode(ListNode node) {
        if (node == null)
            return;
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }
}
