package com.placeholder.leetcode.linkedlist;

import com.placeholder.common.RandomListNode;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _138CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        // 复制next关系
        RandomListNode p = head;
        RandomListNode q = head.next;
        while (p != null) {
            // 插入新next结点
            p.next = new RandomListNode(p.label);
            p.next.next = q;
            // 更新
            p = q;
            if (q != null)
                q = q.next;
        }

        // 复制random关系
        p = head;
        q = head.next;
        while (p != null) {
            // 插入新random结点
            if (p.random == null)
                q.random = null;
            else
                q.random = p.random.next;
            // 更新
            p = p.next.next;
            if (q.next != null)
                q = q.next.next;
        }

        // 解开两个random list
        RandomListNode res = head.next;
        p = head;
        q = head.next;
        while (q.next != null) {
            p.next = q.next;
            p = q;
            if (q.next != null)
                q = q.next;
        }
        p.next = null;
        q.next = null;
        return res;
    }
}
