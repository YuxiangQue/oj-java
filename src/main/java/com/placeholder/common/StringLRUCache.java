package com.placeholder.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yuxiangque
 * @version 2016/4/12
 */
public class StringLRUCache<V> {

    private final int capacity;
    private final TrieMap<KVEntry<V>> cacheMap = new TrieMap<>();
    private List<KVEntry<V>> entryList = new LinkedList<>();

    public StringLRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(String key) {
        KVEntry<V> entry = cacheMap.get(key);
        if (entry != null) {
            entryList.remove(entry);
            entryList.add(0, entry);
            return entry.value;
        } else {
            return null;
        }
    }

    public void set(String key, V value) {
        KVEntry<V> entry = cacheMap.get(key);
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

    private static class KVEntry<V> {
        String key;
        V value;

        public KVEntry(String key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            KVEntry<?> kvEntry = (KVEntry<?>) object;

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

    public static class TestStringLRUCache {

        @Test
        public void test() {
            StringLRUCache<Integer> cache = new StringLRUCache<>(2);
            Assert.assertEquals(0, cache.entryList.size());
            cache.set("2", 1);
            Assert.assertEquals(1, cache.entryList.size());
            Assert.assertEquals(new StringLRUCache.KVEntry<>("2", 1), cache.entryList.get(0));
            cache.set("1", 1);
            Assert.assertEquals(2, cache.entryList.size());
            Assert.assertEquals(new StringLRUCache.KVEntry<>("1", 1), cache.entryList.get(0));
            Assert.assertEquals(new StringLRUCache.KVEntry<>("2", 1), cache.entryList.get(1));
            Assert.assertEquals(1, (int) cache.get("2"));

            Assert.assertEquals(new StringLRUCache.KVEntry<>("2", 1), cache.entryList.get(0));
            Assert.assertEquals(new StringLRUCache.KVEntry<>("1", 1), cache.entryList.get(1));

            cache.set("4", 1);
            Assert.assertEquals(new StringLRUCache.KVEntry<>("4", 1), cache.entryList.get(0));
            Assert.assertEquals(new StringLRUCache.KVEntry<>("2", 1), cache.entryList.get(1));
            Assert.assertEquals(null, cache.get("1"));

            Assert.assertEquals(new StringLRUCache.KVEntry<>("4", 1), cache.entryList.get(0));
            Assert.assertEquals(new StringLRUCache.KVEntry<>("2", 1), cache.entryList.get(1));
            Assert.assertEquals(1, (int) cache.get("2"));

            Assert.assertEquals(new StringLRUCache.KVEntry<>("2", 1), cache.entryList.get(0));
            Assert.assertEquals(new StringLRUCache.KVEntry<>("4", 1), cache.entryList.get(1));
        }
    }
}
