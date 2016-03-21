package com.placeholder.jianzhioffer;

import java.util.HashSet;
import java.util.Set;
import java.util.jar.Pack200;

/**
 * Created by yuxiangque on 2016/3/18.
 */
public class _51Duplicate {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {
            if (set.contains(number)) {
                duplication[0] = number;
                return false;
            }
            set.add(number);
        }
        return true;
    }
}
