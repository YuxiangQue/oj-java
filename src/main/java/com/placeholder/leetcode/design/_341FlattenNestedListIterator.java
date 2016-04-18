package com.placeholder.leetcode.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author yuxiangque
 * @version 2016/4/18
 */
public class _341FlattenNestedListIterator {

    public static void main(String[] args) {
        LazyNestedIterator iterator = new LazyNestedIterator(new ArrayList<NestedInteger>() {{
            add(new List1(new Int1(7), new Int1(2), new List1(new Int1(-1), new Int1(-2))));
            add(new Int1(2));
            add(new List1(new Int1(3), new Int1(4)));
        }});
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        iterator = new LazyNestedIterator(new ArrayList<NestedInteger>() {{
            add(new List1(new List1()));
        }});
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     */

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested ints1.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested ints1
        public Integer getInteger();

        // @return the nested ints1 that this NestedInteger holds, if it holds a nested ints1
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class Int1 implements NestedInteger {

        private final int val;

        public Int1(int val) {
            this.val = val;
        }

        @Override
        public boolean isInteger() {
            return true;
        }

        @Override
        public Integer getInteger() {
            return val;
        }

        @Override
        public List<NestedInteger> getList() {
            return null;
        }
    }

    public static class List1 implements NestedInteger {

        private List<NestedInteger> ints1;

        public List1(NestedInteger... ints) {
            this.ints1 = new ArrayList<>();
            for (NestedInteger int1 : ints) {
                ints1.add(int1);
            }
        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public List<NestedInteger> getList() {
            return ints1;
        }
    }

    public static class LazyNestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack = new Stack<>();


        public LazyNestedIterator(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; --i) {
                stack.push(nestedList.get(i));
            }

        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        // 关键在于把取的过程放在hasNext
        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger current = stack.pop();
                if (!current.isInteger()) {
                    // 拆开倒
                    List<NestedInteger> nestedList1 = current.getList();
                    for (int i = nestedList1.size() - 1; i >= 0; --i) {
                        stack.push(nestedList1.get(i));
                    }
                } else {
                    if (current.getInteger() != null) {  // 说明还有数可以抛出来
                        stack.push(current);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class EagerNestedIterator implements Iterator<Integer> {

        Iterator<Integer> iterator;

        public EagerNestedIterator(List<NestedInteger> nestedList) {
            List<Integer> ints = new ArrayList<>();
            Stack<NestedInteger> stack = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; --i) {
                stack.push(nestedList.get(i));
            }
            while (!stack.isEmpty()) {
                NestedInteger current = stack.pop();
                if (!current.isInteger()) {
                    // 拆开倒
                    List<NestedInteger> nestedList1 = current.getList();
                    for (int i = nestedList1.size() - 1; i >= 0; --i) {
                        stack.push(nestedList1.get(i));
                    }
                } else {
                    if (current.getInteger() != null) {
                        ints.add(current.getInteger());
                    }
                }
            }
            iterator = ints.iterator();
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}
