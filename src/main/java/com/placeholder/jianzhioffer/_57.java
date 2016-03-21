package com.placeholder.jianzhioffer;

import com.placeholder.common.ListNode;

/**
 * Created by yuxiangque on 2016/3/18.
 */
public class _57 {
    // {1,2,3,3,4,4,5}
    static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;

        ListNode pPrev = fakeHead;
        ListNode p = head;
        ListNode q;

        while (p != null) {

            // need delete
            int val = p.val;
            if (p.next != null && p.next.val == val) {
                while (p != null && p.val == val) {
                    p = p.next;
                }
                pPrev.next = p;
            } else {
                pPrev = p;
                p = p.next;
            }
        }
        return fakeHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.buildList(new int[]{1, 2, 3, 3, 4, 4, 5});
        deleteDuplicates(head);
        System.out.println(head);
        head = ListNode.buildList(new int[]{1, 1, 1, 1, 1, 1, 1});
        deleteDuplicates(head);
        System.out.println(head);
    }
}
