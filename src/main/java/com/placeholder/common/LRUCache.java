package com.placeholder.common;

import java.util.HashMap;

/**
 * @author yuxiangque
 * @version 2016/4/12
 */
public class LRUCache<K, V> {

    public final int capacity;
    public final HashMap<K, Node<K, V>> cacheMap = new HashMap<>();
    public Node<K, V> head, tail;
    public int size;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node<K, V>(null, null);
        tail = new Node<K, V>(null, null);

        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        Node<K, V> node = cacheMap.get(key);
        if (node != null) {
            remove(node);
            insertAfter(head, node);
            System.out.println("returns " + node.value);
            return node.value;
        } else {
            System.out.println("returns -1 (not found)");
            return null;
        }
    }

    public void set(K key, V value) {
        Node<K, V> node = cacheMap.get(key);
        if (node == null) {
            if (size >= capacity) {
                Node<K, V> temp = tail.prev;
                System.out.println("evicts key " + temp.key);
                cacheMap.remove(temp.key);
                remove(temp);
                size -= 1;
            }
            node = new Node<>(key, value);
            insertAfter(head, node);
            cacheMap.put(key, node);
            size += 1;
        } else {
            node.key = key;
            node.value = value;
            remove(node);
            insertAfter(head, node);
            cacheMap.put(key, node);
        }
    }

    private void insertAfter(Node<K, V> node, Node<K, V> newNode) {
        newNode.prev = node;
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
    }

    private void remove(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static class Node<K, V> {
        K key;
        V value;

        Node<K, V> next;
        Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (key != null ? !key.equals(node.key) : node.key != null) return false;
            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }
}

