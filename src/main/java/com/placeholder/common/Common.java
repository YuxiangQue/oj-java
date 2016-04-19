package com.placeholder.common;

import java.util.ArrayList;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class Common {
    public static char[][] stringArrayToCharArray2(String[] stirngArray) {
        char[][] charArray2 = new char[stirngArray.length][];
        for (int i = 0; i < stirngArray.length; i++) {
            charArray2[i] = stirngArray[i].toCharArray();
        }
        return charArray2;
    }

    public static ArrayList<Integer> asList(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(i, nums[i]);
        }
        return list;
    }

    public static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
