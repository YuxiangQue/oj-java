package com.placeholder.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SizeOfArrayAfterRepeatedDeletionOfLIS {


    public static void main(String[] args) {
        solve(Arrays.asList(1, 2, 5, 3, 6, 4, 1));
    }

    public static List<Integer> solve(List<Integer> nums) {
        List<Integer> ans = new ArrayList<>(nums);
        while (ans.size() > 0) {
            List<Integer> lis = LIS(ans);
            if (lis.size() < 2) {
                break;
            }
            for (int i = lis.size() - 1; i >= 0; i--) {
                ans.remove((int) lis.get(i));
            }
        }
        return ans;
    }

    public static List<Integer> LIS(List<Integer> nums) {
        int n = nums.size();
        int[] dp = new int[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            parent[i] = i;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i - 1; ++j) { // 这里需要顺序
                if (nums.get(j) < nums.get(i)) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        parent[i] = j;
                    }
                }
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < n; ++i) {
            if (dp[maxIndex] < dp[i]) {
                maxIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        int index = maxIndex;
        while (true) {
            ans.add(0, index);
            if (parent[index] == index) {
                break;
            }
            index = parent[index];
        }
        return ans;
    }

}
