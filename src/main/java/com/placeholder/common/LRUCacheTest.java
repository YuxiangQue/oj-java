package com.placeholder.common;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {
    @Test
    public void test() {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        Assert.assertEquals(0, cache.size);
        cache.set(2, 1);
        Assert.assertEquals(1, cache.size());
        Assert.assertEquals(new LRUCache.Node<>(2, 1), cache.head.next);
        cache.set(1, 1);
        Assert.assertEquals(2, cache.size());
        Assert.assertEquals(new LRUCache.Node<>(1, 1), cache.head.next);
        Assert.assertEquals(new LRUCache.Node<>(2, 1), cache.head.next.next);
        Assert.assertEquals(1, (int) cache.get(2));

        Assert.assertEquals(new LRUCache.Node<>(2, 1), cache.head.next);
        Assert.assertEquals(new LRUCache.Node<>(1, 1), cache.head.next.next);

        cache.set(4, 1);
        Assert.assertEquals(new LRUCache.Node<>(4, 1), cache.head.next);
        Assert.assertEquals(new LRUCache.Node<>(2, 1), cache.head.next.next);
        Assert.assertEquals(null, cache.get(1));

        Assert.assertEquals(new LRUCache.Node<>(4, 1), cache.head.next);
        Assert.assertEquals(new LRUCache.Node<>(2, 1), cache.head.next.next);
        Assert.assertEquals(1, (int) cache.get(2));

        Assert.assertEquals(new LRUCache.Node<>(2, 1), cache.head.next);
        Assert.assertEquals(new LRUCache.Node<>(4, 1), cache.head.next.next);
    }
}
