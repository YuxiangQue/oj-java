package com.placeholder.leetcode;

public class _457CircularArrayLoop {

    public static boolean circularArrayLoop(int[] nums) {
        int length = nums.length;
        if (length == 0)
            return false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                continue;
            int j = i;
            int depth = 0;
            while (depth < length) {
                int k = (j + nums[j] + length) % length;
                if (j == k)
                    nums[j] = 0;
                if (nums[j] * nums[k] <= 0)
                    break;
                j = k;
                depth += 1;
            }
            if (depth == nums.length)
                return true;
            j = i;
            while (depth > 0) {
                int k = (j + nums[j] + length) % length;
                nums[j] = 0;
                j = k;
                depth -= 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        System.out.println(circularArrayLoop(new int[]{-1, 2}));
        System.out.println(circularArrayLoop(new int[]{}));
        System.out.println(circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
    }
}


