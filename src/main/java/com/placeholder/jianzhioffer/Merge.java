package com.placeholder.jianzhioffer;

import com.placeholder.builtin.ListNode;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * @author yuxiangque
 * @version 2016/4/20
 */
public class Merge {

    public static void main(String[] args) {
        ListNode list1 = ListNode.buildList(1, 3, 5);
        ListNode list2 = ListNode.buildList(2, 4, 6, 7);
        ListNode list3 = Merge(list1, list2);
        return;
    }

    public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode p1 = list1;  // 保存下一个要合并的节点
        ListNode p2 = list2;
        ListNode list3;

        if (p1.val < p2.val) {
            list3 = p1;
            p1 = p1.next;
        } else {
            list3 = p2;
            p2 = p2.next;
        }

        ListNode p3 = list3;   // p3保存list3的最后一个节点

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p3.next = p1;
                p3 = p3.next;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p3 = p3.next;
                p2 = p2.next;
            }
        }

        if (p1 == null) {
            p3.next = p2;
        }
        if (p2 == null) {
            p3.next = p1;
        }
        return list3;
    }
}
