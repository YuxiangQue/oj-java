package com.placeholder.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/missing-number/
 * #Array
 * #Math
 * #BitManipulation
 *
 * @author yuxiangque
 * @version 2016/3/26
 */
public class _268MissingNumber {

    int missingNumber_BinarySearch(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    int missNumber_Sum(int[] nums) {
        int len = nums.length;
        int sum = (len) * (len + 1) / 2;
        for (int i = 0; i < len; i++)
            sum -= nums[i];
        return sum;
    }

    int missingNumber_SumAvoidOverflow(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += i;
            sum -= nums[i];
        }
        sum += nums.length;
        return sum;
    }

    // XOR
    // Time: O(n)
    // Space: O(1)
    int missNumber_XOR(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }


    // Hash
    // Time: O(n)
    // Space: O(n)
    int missingNumber_Hash(int[] nums) {
        int length = nums.length;
        int[] hash = new int[length + 1];
        Arrays.fill(hash, 1);
        for (int i = 0; i < length + 1; ++i) {
            if (hash[i] == 0)
                return i;
        }
        return -1;
    }

    // Time: O(n)
    // Space: O(1)
    int missingNumber_Swap(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]) {
                // swap nums[num[i]] and num[i]
                if (nums[i] < nums.length) {
                    int tmp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = tmp;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i)
                return i;
        }
        return nums.length;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, missingNumber_Swap(new int[]{0, 1, 3}));
        Assert.assertEquals(2, missingNumber_Swap(new int[]{0, 1, 3, 4}));
        Assert.assertEquals(2, missingNumber_Swap(new int[]{0, 1, 4, 3}));
        Assert.assertEquals(2, missingNumber_Swap(new int[]{1, 0, 4, 3}));
    }
}
