package com.placeholder.hihoCoder.weekly;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _108MemoryAllocatingAlgorithm {

    public Block head;
    protected int maxMem = 0;
    protected int key = 1;
    protected Block tail;
    protected Map<Integer, Block> map = new HashMap<>();

    public _108MemoryAllocatingAlgorithm(int maxMem) {
        this.maxMem = maxMem;
        head = new Block(-1, 0);
        tail = new Block(-1, 0);
        Block p = new Block(0, maxMem);
        p.prev = head;
        p.next = tail;
        head.next = p;
        tail.prev = p;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        _108MemoryAllocatingAlgorithm ma = new _108MemoryAllocatingAlgorithm(m);
        int deleteKey = 0;
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            while (true) {
                Block p = ma.findEmptyBlock(k);
                if (p != null) {
                    ma.insert(p, i + 1, k);
                    break;
                } else {
                    deleteKey += 1;
                    ma.delete(deleteKey);
                }
            }
            // System.out.println(k + "=>" + ma.head);
        }
        Block p = ma.head.next;
        int start = 0;
        while (p.key != -1) {
            if (p.key != 0) {
                System.out.println(p.key + " " + start);
                start += p.length;
            }
            p = p.next;
        }
    }

    protected void insert(Block emptyBlock_, int key_, int length_) {
        if (emptyBlock_.length == length_) {
            emptyBlock_.key = key_;
        } else {
            Block p = new Block(0, emptyBlock_.length - length_);
            emptyBlock_.key = key_;
            emptyBlock_.length = length_;

            p.prev = emptyBlock_;
            p.next = emptyBlock_.next;
            p.next.prev = p;
            emptyBlock_.next = p;
        }
        map.put(key_, emptyBlock_);
    }

    protected void delete(int key_) {
        Block p = map.get(key_);
        Block prev = p.prev;
        if (prev.key == 0) {
            p.length = p.length + prev.length;
            p.prev = prev.prev;
            p.prev.next = p;
        }
        Block next = p.next;
        if (next.key == 0) {
            p.length = p.length + next.length;
            p.next = next.next;
            p.next.prev = p;
        }
        p.key = 0;
    }

    private Block findEmptyBlock(int length_) {
        Block p = head.next;
        while (p.key != -1) {
            if (p.key == 0 && p.length >= length_)
                return p;
            p = p.next;
        }
        return null;
    }

    protected static class Block {
        public int key;
        public int length;
        public Block prev;
        public Block next;

        public Block(int key_, int length_) {
            key = key_;
            length = length_;
        }

        @Override
        public String toString() {
            return "" +
                    "{" + key +
                    ":" + length +
                    "}->" + next;
        }
    }
}
