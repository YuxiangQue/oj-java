package com.placeholder.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author yuxiangque
 * @version 2016/4/13
 */
public class _16ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null)
            return 0;
        if (nums.length < 3) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }

        int closetSum = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);  // 升序  O(n*log(n))
        for (int i = 0; i < nums.length; ++i) {  // O(n2)

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    if (Math.abs(sum - target) < Math.abs(closetSum - target))
                        closetSum = sum;
                    ++left;
                } else if (sum > target) {
                    if (Math.abs(sum - target) < Math.abs(closetSum - target))
                        closetSum = sum;
                    --right;
                } else {
                    closetSum = sum;
                    break;
                }
            }
            // 跳过相同数
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                ++i;
            }
        }
        return closetSum;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, threeSumClosest(new int[]{-1, 2, 1, -4, 4}, 1));
        Assert.assertEquals(2, threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
