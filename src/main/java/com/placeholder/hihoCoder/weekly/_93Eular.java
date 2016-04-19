package com.placeholder.hihoCoder.weekly;

import com.placeholder.common.NumberTheory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho93/problems 数论二·Eular质数筛法
 *
 * @author yuxiangque
 * @version 2016/4/10
 */
public class _93Eular {

    // 快速得求解[1,N]这个区间内素数
    public static int numOfPrimes(int n) {
        if (n < 2)
            return 0;
        int[] filter = new int[n + 1];
        Arrays.fill(filter, 1);

        for (int i = 0; i < n; i++) {
            if (filter[i] == 0) {
            } else if (NumberTheory.isPrime(i)) {
                for (int j = 2; i * j <= n; j++) {
                    filter[i * j] = 0;
                }
            } else {
                filter[i] = 0;
            }
        }
        int count = 0;
        for (int temp : filter) {
            if (temp == 1)
                ++count;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(numOfPrimes(n));
    }

    @Test
    public void test() {
        Assert.assertEquals(0, numOfPrimes(-1));
        Assert.assertEquals(0, numOfPrimes(0));
        Assert.assertEquals(0, numOfPrimes(1));
        Assert.assertEquals(1, numOfPrimes(2));
        Assert.assertEquals(2, numOfPrimes(3));
        Assert.assertEquals(10, numOfPrimes(30));
    }
}
