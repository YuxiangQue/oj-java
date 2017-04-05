package com.placeholder.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _135Candy {

    public static void main(String[] args) {
        System.out.println(candy(new int[]{7, 8, 2, 5, 4, 3, 0, 9, 4, 3, 2, 7, 8, 6}));
    }

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i - 1] < ratings[i]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i + 1] + 1, candy[i]);
            }
        }
        return IntStream.of(candy).sum();
    }
}
