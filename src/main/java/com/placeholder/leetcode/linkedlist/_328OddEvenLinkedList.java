package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.ListNode;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _328OddEvenLinkedList {
    public static void main(String[] args) {
        System.out.println(new _328OddEvenLinkedList().oddEvenList(null));

        ListNode head = new ListNode(1);
        System.out.println(new _328OddEvenLinkedList().oddEvenList(head));

        head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(new _328OddEvenLinkedList().oddEvenList(head));

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        System.out.println(new _328OddEvenLinkedList().oddEvenList(head));

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new _328OddEvenLinkedList().oddEvenList(head));
    }

    /**
     * https://leetcode.com/problems/odd-even-linked-list/
     * [1 2 3 4 5]
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
