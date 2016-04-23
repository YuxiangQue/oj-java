package com.placeholder.test.msbop2016;


public class _02 {

    public static int Puzzle(int[] nums) {
        int b = 1;
        for (int num : nums) {
            b *= num;
        }
        return (int) Math.pow(b, 1 / nums.length);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1};
        System.out.println(Puzzle(arr) + "[1]");

        arr = new int[]{2};
        System.out.println(Puzzle(arr) + "[2]");

        arr = new int[]{4, 14, 7};
        System.out.println(Puzzle(arr) + "[1]");

        arr = new int[]{5, 10};
        System.out.println(Puzzle(arr) + "[1]");

        arr = new int[]{1, 3};
        System.out.println(Puzzle(arr) + "[1]");

        arr = new int[]{1, 2, 3};
        System.out.println(Puzzle(arr) + "[1]");
    }
}
