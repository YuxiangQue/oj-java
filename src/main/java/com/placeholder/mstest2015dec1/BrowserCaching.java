package com.placeholder.mstest2015dec1;

import com.placeholder.common.LRUCache;

/**
 * http://hihocoder.com/contest/mstest2015dec1/problem/1
 *
 * @author yuxiangque
 * @version 2016/4/12
 */
public class BrowserCaching {

    public static void main(String[] args) {
        BrowserCacher cacher = new BrowserCacher(2);

        System.out.println(cacher.visit("www.bing.com"));
        System.out.println(cacher.visit("www.microsoft.com"));
        System.out.println(cacher.visit("www.microsoft.com"));
        System.out.println(cacher.visit("windows.microsoft.com"));
        System.out.println(cacher.visit("www.bing.com"));
    }

    static class BrowserCacher {

        LRUCache<String, String> cache;

        public BrowserCacher(int capacity) {
            cache = new LRUCache<>(capacity);
        }

        public String visit(String url) {
            if (cache.get(url) == null) {
                cache.set(url, "");
                return "Internet";
            } else {
                return "Cache";
            }
        }
    }
}
