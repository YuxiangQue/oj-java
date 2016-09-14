package com.placeholder.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yuxiangque
 * @version 2016/8/14
 */
public class _374GuessNumberHigherOrLower {

    int guess(int num) {
        return num == 1702766719 ? 0 : (num > 1702766719 ? -1 : 1);
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (true) {
            int mid = left + (right - left) / 2;
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            } else if (guess == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(1702766719, guessNumber(2126753390));
    }
}
