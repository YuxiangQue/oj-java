package com.placeholder.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuxiangque
 * @version 2016/4/12
 */
public class LRUCache<K, V> {

    private final int capacity;
    private final HashMap<K, KVEntry<K, V>> cacheMap = new HashMap<>();
    private List<KVEntry<K, V>> entryList = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        KVEntry<K, V> entry = cacheMap.get(key);
        if (entry != null) {
            entryList.remove(entry);
            entryList.add(0, entry);
            return entry.value;
        } else {
            return null;
        }
    }

    public void set(K key, V value) {
        KVEntry<K, V> entry = cacheMap.get(key);
        if (entry == null) {
            if (entryList.size() >= capacity) {
                cacheMap.remove(entryList.remove(entryList.size() - 1).key);
            }
            entry = new KVEntry<>(key, value);
            entryList.add(0, entry);
            cacheMap.put(key, entry);
        } else {
            entry.key = key;
            entry.value = value;
            entryList.remove(entry);
            entryList.add(0, entry);
            cacheMap.put(key, entry);
        }
    }

    @Test
    public void test() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        Assert.assertEquals(0, cache.entryList.size());
        cache.set(2, 1);
        Assert.assertEquals(1, cache.entryList.size());
        Assert.assertEquals(new LRUCache.KVEntry<>(2, 1), cache.entryList.get(0));
        cache.set(1, 1);
        Assert.assertEquals(2, cache.entryList.size());
        Assert.assertEquals(new LRUCache.KVEntry<>(1, 1), cache.entryList.get(0));
        Assert.assertEquals(new LRUCache.KVEntry<>(2, 1), cache.entryList.get(1));
        Assert.assertEquals(1, (int) cache.get(2));

        Assert.assertEquals(new LRUCache.KVEntry<>(2, 1), cache.entryList.get(0));
        Assert.assertEquals(new LRUCache.KVEntry<>(1, 1), cache.entryList.get(1));

        cache.set(4, 1);
        Assert.assertEquals(new LRUCache.KVEntry<>(4, 1), cache.entryList.get(0));
        Assert.assertEquals(new LRUCache.KVEntry<>(2, 1), cache.entryList.get(1));
        Assert.assertEquals(null, cache.get(1));

        Assert.assertEquals(new LRUCache.KVEntry<>(4, 1), cache.entryList.get(0));
        Assert.assertEquals(new LRUCache.KVEntry<>(2, 1), cache.entryList.get(1));
        Assert.assertEquals(1, (int) cache.get(2));

        Assert.assertEquals(new LRUCache.KVEntry<>(2, 1), cache.entryList.get(0));
        Assert.assertEquals(new LRUCache.KVEntry<>(4, 1), cache.entryList.get(1));
    }

    private static class KVEntry<K, V> {
        K key;
        V value;

        public KVEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            KVEntry<?, ?> kvEntry = (KVEntry<?, ?>) object;

            if (key != null ? !key.equals(kvEntry.key) : kvEntry.key != null) return false;
            return value != null ? value.equals(kvEntry.value) : kvEntry.value == null;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "KVEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
