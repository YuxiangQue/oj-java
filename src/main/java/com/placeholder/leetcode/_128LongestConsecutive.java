package com.placeholder.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yuxiangque
 * @version 2016/9/5
 */
public class _128LongestConsecutive {

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int num : nums) {
            int down = num - 1;
            while (set.contains(down)) {
                set.remove(down);
                --down;
            }
            int up = num + 1;
            while (set.contains(up)) {
                set.remove(up);
                ++up;
            }
            longest = Math.max(longest, up - down - 1);
        }
        return longest;
    }
}
