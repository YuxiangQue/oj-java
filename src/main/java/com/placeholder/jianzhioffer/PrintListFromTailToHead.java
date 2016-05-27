package com.placeholder.jianzhioffer;

import com.placeholder.predef.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}
