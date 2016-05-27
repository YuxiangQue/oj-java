package com.placeholder.leetcode.linkedlist;

import com.placeholder.predef.ListNode;
import com.placeholder.predef.TreeNode;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _109ConvertSortedListToBST {


    /**
     *
     * @param head
     * @param tail
     * @return
     */
    TreeNode _sortedListToBST(ListNode head, ListNode tail) {
        if (head == tail) {  // empty
            return null;
        }
        if (head.next == tail) {  // single
            return new TreeNode(head.val);
        }
        // 找到中间结点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 递归
        TreeNode root = new TreeNode(slow.val);
        root.left = _sortedListToBST(head, slow);
        root.right = _sortedListToBST(slow.next, tail);
        return root;
    }

    TreeNode sortedListToBST(ListNode head) {
        return _sortedListToBST(head, null);
    }
}
