package com.placeholder.leetcode.hashtable;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _268MissingNumber {
    int missingNumber(List<Integer> nums) {
        int length = nums.size();
        int[] hash = new int[length + 1];
        Arrays.fill(hash, 1);
        for (int i = 0; i < length + 1; ++i) {
            if (hash[i] == 0)
                return i;
        }
        return -1;
    }
}
