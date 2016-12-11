package com.placeholder.common;

class LFUCacheTest {
    public static void main(String[] args) {
        LFUCache<Integer, Integer> cache = new LFUCache<>(2);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.get(1);
        cache.set(3, 3);
        cache.get(2);
        cache.get(3);
        cache.set(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}
