package com.placeholder.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _66PlusOne {
    List<Integer> plusOne(List<Integer> digits) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        int size = digits.size();
        for (int i = size - 1; i >= 0; --i) {
            int val = digits.get(i);
            val = (val + 1 + carry) / 10;
            carry = (val + 1 + carry) % 10;
            result.add(0, val);
        }
        if (carry == 1)
            result.add(0, 1);
        return result;
    }
}
