package com.placeholder.ntest2015septdev;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * http://hihocoder.com/contest/ntest2015septdev/problem/1
 *
 * @author yuxiangque
 * @version 2016/4/7
 */
public class AmusingDigits {

    @Test
    public void test() {
        Assert.assertEquals(0, amusingDigits("6097"));
        Assert.assertEquals(2, amusingDigits("97069706"));
        Assert.assertEquals(1, amusingDigits("997776600069"));
        Assert.assertEquals(2, amusingDigits("123901370997606"));
    }

    private int amusingDigits(String digits) {
        Map<Integer, Character> lengthCharMap = new HashMap<>();
        Set<Integer> used = new HashSet<>();
        lengthCharMap.put(0, '9');
        lengthCharMap.put(1, '7');
        lengthCharMap.put(2, '0');
        lengthCharMap.put(3, '6');
        int count = 0;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            for (int j = i; j < digits.length(); j++) {
                char expectedCh = lengthCharMap.get(indexes.size());
                if (!used.contains(j) && digits.charAt(j) == expectedCh) {
                    indexes.add(j);
                    if (indexes.size() == lengthCharMap.size()) {
                        ++count;
                        used.addAll(indexes);
                        indexes.clear();
                        break;
                    }
                }
            }
            indexes.clear();
        }
        return count;
    }

    private void dfs(int[] count) {

    }
}
