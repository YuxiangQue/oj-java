package com.placeholder.leetcode.design;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _284PeekingIterator {
    @Test
    public void test() {
        List<Integer> data = Arrays.asList(1, 2, 3);
        PeekingIterator peekingIterator = new PeekingIterator(data.iterator());
        assertEquals(1, (int) peekingIterator.peek());
        assertEquals(1, (int) peekingIterator.next());
        assertEquals(2, (int) peekingIterator.peek());
        assertEquals(2, (int) peekingIterator.next());
        assertEquals(3, (int) peekingIterator.peek());
        assertEquals(true, peekingIterator.hasNext());
        assertEquals(3, (int) peekingIterator.next());
        assertEquals(false, peekingIterator.hasNext());
    }

    // Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
    static class PeekingIterator implements Iterator<Integer> {

        private Iterator<Integer> iterator;
        private boolean peeked = false;
        private Integer peekValue = 0;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (peeked) {
                return peekValue;
            }
            peeked = true;
            peekValue = iterator.next();
            return peekValue;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (peeked) {
                peeked = false;
                return peekValue;
            }
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            if (peeked)
                return true;
            return iterator.hasNext();
        }
    }
}
