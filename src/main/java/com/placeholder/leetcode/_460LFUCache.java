package com.placeholder.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class _460LFUCache {
    public static void main(String[] args) {
//        LFUCache cache = new LFUCache(2);
//        cache.set(1, 1);
//        cache.set(2, 2);
//        cache.get(1);
//        cache.set(3, 3);
//        cache.get(2);
//        cache.get(3);
//        cache.set(4, 4);
//        cache.get(1);
//        cache.get(3);
//        cache.get(4);

        LFUCache cache = new LFUCache(3);
        cache.set(2, 2);
        cache.set(1, 1);
        cache.get(2);
        cache.get(1);
        cache.get(2);
        cache.set(3, 3);
        cache.set(4, 4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.get(4);
    }

    public static class LFUCache {
        public final int capacity;
        Map<Integer, Integer> keyToValue;
        Map<Integer, Node> keyToNode;
        Node head, tail;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            head.prev = tail;
            tail.next = head;
            tail.prev = head;
            keyToValue = new HashMap<>(capacity, 1.0f);
            keyToNode = new HashMap<>(capacity, 1.0f);
        }

        public int get(int key) {
            if (keyToValue.containsKey(key)) increase(key);
            int temp = keyToValue.getOrDefault(key, -1);
            return temp;
        }

        private void increase(int key) {
            Node node = keyToNode.get(key);
            node.keys.remove(key);
            if (node.next == null)
                node.next = new Node(node, null, 1 + node.freq, key);
            else if (node.next.freq == node.freq + 1)
                node.next.keys.add(key);
            else
                node.next = node.next.prev = new Node(node, node.next, node.freq + 1, key);
            keyToNode.put(key, node.next);
            if (node.keys.isEmpty())
                remove(node);
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void set(int key, int value) {
            if (capacity == 0) return;
            if (keyToValue.containsKey(key)) {
                keyToValue.put(key, value);
                increase(key);
            } else { // new
                if (keyToValue.size() == capacity)
                    remove();
                keyToValue.put(key, value);
                add(key);
            }
        }

        private void add(int key) {
            if (head.next != tail && head.next.freq == 1) {
                head.next.keys.add(key);
            } else {
                head.next = head.next.prev = new Node(head, head.next, 1, key);
            }
            keyToNode.put(key, head.next);
        }

        private void remove() {
            if (head.next == tail)
                return;
            Node first = head.next;
            int leastRecent = first.keys.iterator().next();
            first.keys.remove(leastRecent);
            if (first.keys.isEmpty())
                remove(first);
            keyToNode.remove(leastRecent);
            int value = keyToValue.remove(leastRecent);
        }


        static class Node {
            Node next, prev;
            LinkedHashSet<Integer> keys = new LinkedHashSet<Integer>();
            int freq;

            public Node() {
            }

            public Node(Node prev, Node next, int freq, int key) {
                this.prev = prev;
                this.next = next;
                this.freq = freq;
                this.keys.add(key);
            }
        }
    }
}
