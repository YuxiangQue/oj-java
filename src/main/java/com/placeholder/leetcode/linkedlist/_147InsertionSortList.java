package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * @author 阙宇翔
 * @version 2016/3/21
 */
public class _147InsertionSortList {
    ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode i, j, k;
        for (i = head; i != null; i = i.next) {
            k = i;
            for (j = i.next; j != null; j = j.next) {
                if (j.val < k.val)
                    k = j;
            }
            int c = i.val;
            i.val = k.val;
            k.val = c;
        }
        return head;
    }
}
