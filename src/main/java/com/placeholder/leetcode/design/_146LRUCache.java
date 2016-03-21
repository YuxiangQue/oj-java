package com.placeholder.leetcode.design;

import java.util.HashMap;

/**
 * Least Recently Used
 * http://www.cnblogs.com/lzrabbit/p/3734850.html
 * http://www.hawstein.com/posts/lru-cache-impl.html
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _146LRUCache {

    private final int capacity;
    private final HashMap<Integer, Node> cacheMap;
    private LinkedList list;

    public _146LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList();
        this.cacheMap = new HashMap<>();
    }

    public static void main(String[] args) {
        _146LRUCache cache = new _146LRUCache(2);
        cache.list.pritnlnList();

        cache.set(2, 1);
        cache.list.pritnlnList(); // (2,1)

        cache.set(1, 1);
        cache.list.pritnlnList(); // (1,1) (2,1)

        System.out.println(cache.get(2));
        cache.list.pritnlnList(); // (2,1) (1,1)

        cache.set(4, 1);
        cache.list.pritnlnList(); // (4,1) (2,1)

        System.out.println(cache.get(1));
        cache.list.pritnlnList();  // (4,1) (2,1)

        System.out.println(cache.get(2));
        cache.list.pritnlnList(); // (4,1) (2,1)
        return;
    }

    public int get(int key) {
        Node node = cacheMap.get(key);
        // Node存在，剥离后加入头部
        if (node != null) {
            list.detachAny(node);
            list.attachFirst(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        Node node = cacheMap.get(key);
        // Node不存在
        if (node == null) {
            if (cacheMap.size() >= capacity) {
                // 从尾部剥离
                cacheMap.remove(list.detachLast().key);
            }
            node = new Node();
            node.key = key;
            node.value = value;
            list.attachFirst(node);
            cacheMap.put(key, node);
        } else {
            node.key = key;
            node.value = value;
            list.detachAny(node);
            list.attachFirst(node);
            cacheMap.put(key, node);
        }
    }

    private final static class Node {
        Node next;
        Node prev;
        int value;
        int key;
    }

    private final static class LinkedList {

        public Node head;
        public Node tail;

        public LinkedList() {
            // virtual nodes
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public void pritnlnList() {
            for (Node node = head.next; node != tail; node = node.next) {
                System.out.print("(" + node.key + ", " + node.value + ")");
            }
            System.out.println();
        }


        /**
         * 移除最后一个Node
         *
         * @return 返回被移除的Node
         */
        public Node detachLast() {
            if (head.next == tail) {
                return null;
            }
            Node node = tail.prev;

            tail.prev = node.prev;
            tail.prev.next = tail;

            node.next = node.prev = null;

            return node;
        }

        /**
         * 移除任意Node
         *
         * @param node 要移除的Node
         * @return 返回被剥离的Node，当Node为head或tail时返回null
         */
        public Node detachAny(Node node) {
            if (node == head || node == tail) {
                return null;
            }

            // detach
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;

            return node;
        }


        /**
         * 添加Node至头部
         *
         * @param node 要移除的Node
         * @return 返回被添加的Node
         */
        public Node attachFirst(Node node) {
            if (node == head || node == tail) {
                return null;
            }

            // add to head
            node.next = head.next;
            node.prev = head;

            head.next = node;
            node.next.prev = node;

            return node;
        }
    }
}
