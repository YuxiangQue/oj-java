package com.placeholder.os;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FirstInOutPage {

    public static void main(String[] args) {
        System.out.println(countCacheMiss(3, new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0}) + "[12]");
        System.out.println(countCacheMiss(2, new int[]{2, 3, 1, 3, 2, 1, 4, 3, 2}) + "[7]");
    }

    public static int countCacheMiss(int maxCacheSize, int[] pageRequests) {
        FIFOCache cache = new FIFOCache(maxCacheSize);
        int missCounter = 0;
        for (int pageRequest : pageRequests) {
            missCounter += (cache.query(pageRequest) == null ? 1 : 0);
        }
        return missCounter;
    }

    public static class FIFOCache {

        private final int capacity;
        private final HashMap<Integer, FIFOCache.KVEntry<Integer>> cacheMap = new HashMap<>();
        private List<KVEntry<Integer>> entryList = new LinkedList<>();

        public FIFOCache(int capacity) {
            this.capacity = capacity;
        }

        public Integer query(Integer key) {

            // get
            KVEntry<Integer> entry = cacheMap.get(key);
            if (entry != null) {  // 缓存不为空则返回
                return entry.key;
            } else {   // 缓存为空则刷新缓存

                // 缓存已满则移除最先进入的缓存
                if (entryList.size() >= capacity) {
                    cacheMap.remove(entryList.remove(0).key);
                }
                // 同时加入新缓存
                entry = new KVEntry<>(key);
                entryList.add(entry);
                cacheMap.put(key, entry);
                return null;
            }
        }

        private static class KVEntry<K> {
            K key;

            public KVEntry(K key) {
                this.key = key;
            }

            @Override
            public boolean equals(Object object) {
                if (this == object) return true;
                if (object == null || getClass() != object.getClass()) return false;
                KVEntry<?> kvEntry = (KVEntry<?>) object;
                return key != null ? key.equals(kvEntry.key) : kvEntry.key == null;

            }

            @Override
            public int hashCode() {
                return key != null ? key.hashCode() : 0;
            }
        }
    }
}
