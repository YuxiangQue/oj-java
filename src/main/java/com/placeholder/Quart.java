package com.placeholder;

public class Quart {

    private static int sumRange(int[] sum, int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static boolean resolve(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int m1 = 0;
        int m3 = n - 1;
        int m2 = 0;
        while (m1 < m3) {
            int sum1 = sumRange(sum, 0, m1 - 1);
            int sum4 = sumRange(sum, m3 + 1, n - 1);
            if (sum1 < sum4) {
                m1 += 1;
            } else if (sum1 > sum4) {
                m3 -= 1;
            } else {
                int curr = Math.max(m2, m1 + 1);
                while (curr < m3 && sumRange(sum, m1 + 1, curr - 1) != sum1) {
                    curr += 1;
                }
                m2 = curr;
                if (m2 < m3 && sumRange(sum, m2 + 1, m3 - 1) == sum1) {
                    return true;
                }
                ++m1;
                --m3;
            }
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(resolve(new int[]{1, 1, 1, 1, 1, 1, 1}));
        System.out.println(resolve(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
