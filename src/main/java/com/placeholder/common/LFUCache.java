package com.placeholder.common;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache<K, V> {
    public final int capacity;
    Map<K, V> keyToValue;
    Map<K, Node<K>> keyToNode;
    Node<K> head, tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        head = new Node<K>();
        tail = new Node<K>();
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
        keyToValue = new HashMap<K, V>(capacity, 1.0f);
        keyToNode = new HashMap<>(capacity, 1.0f);
    }

    public V get(K key) {
        if (keyToValue.containsKey(key)) increase(key);
        V temp = keyToValue.getOrDefault(key, null);
        System.out.println("returns " + (temp == null ? "-1 (not found)" : temp));
        return temp;
    }

    private void increase(K key) {
        Node<K> node = keyToNode.get(key);
        node.keys.remove(key);
        if (node.next == null)
            node.next = new Node<K>(node, null, 1 + node.count, key);
        else if (node.next.count == node.count + 1)
            node.next.keys.add(key);
        else
            node.next = node.next.prev = new Node<K>(node, node.next, node.count + 1, key);
        keyToNode.put(key, node.next);
        if (node.keys.isEmpty())
            remove(node);
    }

    private void remove(Node<K> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void set(K key, V value) {
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

    private void add(K key) {
        if (head.next == tail) {
            head.next = head.next.prev = new Node<K>(head, head.next, 1, key);
            keyToNode.put(key, head.next);
        } else {
            Node<K> first = head.next;
            if (first.count == 1) {
                first.keys.add(key);
            } else {
                head.next = head.next.prev = new Node<K>(head, head.next, 1, key);
                keyToNode.put(key, head.next);
            }
        }
    }

    private void remove() {
        if (head.next == tail)
            return;
        Node<K> first = head.next;
        K leastRecent = first.keys.iterator().next();
        first.keys.remove(leastRecent);
        if (first.keys.isEmpty())
            remove(first);
        keyToNode.remove(leastRecent);
        V value = keyToValue.remove(leastRecent);
        System.out.println("evicts key " + leastRecent);
    }


    static class Node<K> {
        Node<K> next, prev;
        LinkedHashSet<K> keys = new LinkedHashSet<K>();
        int count;

        public Node() {
        }

        public Node(Node<K> prev, Node<K> next, int count, K key) {
            this.prev = prev;
            this.next = next;
            this.count = count;
            this.keys.add(key);
        }
    }

}
