package com.placeholder.leetcode.design;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author yuxiangque
 * @version 2016/8/5
 */
public class _380InsertDeleteGetRandomO1 {
    @Test
    public void test() {
        RandomizedSet randomSet = new RandomizedSet();

        Assert.assertEquals(true, randomSet.insert(1));

        Assert.assertEquals(false, randomSet.remove(2));

        Assert.assertEquals(true, randomSet.insert(2));

        randomSet.getRandom();

        Assert.assertEquals(true, randomSet.remove(1));

        Assert.assertEquals(false, randomSet.insert(2));

        Assert.assertEquals(2, randomSet.getRandom());

        Assert.assertEquals(true, randomSet.insert(3));

        Assert.assertEquals(true, randomSet.remove(2));
    }

    @Test
    public void test2() {
        RandomizedSet randomSet = new RandomizedSet();

        Assert.assertEquals(false, randomSet.remove(0));

        Assert.assertEquals(false, randomSet.remove(0));

        Assert.assertEquals(true, randomSet.insert(0));

        Assert.assertEquals(0, randomSet.getRandom());

        Assert.assertEquals(true, randomSet.remove(0));

        Assert.assertEquals(true, randomSet.insert(0));
    }

    @Test
    public void test3() {
        RandomizedSet randomSet = new RandomizedSet();
        Assert.assertEquals(true, randomSet.insert(3));
        Assert.assertEquals(false, randomSet.insert(3));
        Assert.assertEquals(true, randomSet.insert(1));
        Assert.assertEquals(true, randomSet.remove(3));
        Assert.assertEquals(true, randomSet.insert(0));
        Assert.assertEquals(true, randomSet.remove(0));
    }

    public static class RandomizedSet {

        private Map<Integer, Integer> valueIndexMap = new HashMap<>();
        private List<Integer> list = new ArrayList<>();
        private Random random = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {

        }

        /**
         * Inserts a value to the valueIndexMap. Returns true if the valueIndexMap did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (valueIndexMap.containsKey(val)) {
                return false;
            }
            list.add(val);
            valueIndexMap.put(val, list.size() - 1);
            return true;
        }

        /**
         * Removes a value from the valueIndexMap. Returns true if the valueIndexMap contained the specified element.
         */
        public boolean remove(int val) {
            if (!valueIndexMap.containsKey(val)) {
                return false;
            }
            int index = valueIndexMap.remove(val);
            list.remove(index);
            if (index != list.size()) {  // if the removed value is the last one, no need for replace
                int lastVal = list.remove(list.size() - 1);
                list.add(index, lastVal);
                valueIndexMap.put(lastVal, index);
            }
            return true;
        }

        /**
         * Get a random element from the valueIndexMap.
         */
        public int getRandom() {
            int index = random.nextInt(list.size());
            return list.get(index);
        }
    }
}
