package com.placeholder;

import org.junit.Assert;
import org.junit.Test;

/**
 * 从有序数组中找第一个比k小的数
 *
 * @author yuxiangque
 * @version 2016/4/12
 */
public class SearchNumLessThan {

    public int bsearchNumLessThan(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (arr[mid] < k) {
                left = mid;
            } else if (arr[mid] > k) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        if (arr[left] < k) {
            return left;
        }
        return -(left + 1);
    }

    @Test
    public void test() {
        Assert.assertEquals(4, bsearchNumLessThan(new int[]{1, 2, 4, 4, 4, 5, 6}, 5));
        Assert.assertEquals(1, bsearchNumLessThan(new int[]{1, 2, 4, 4, 4, 5, 6}, 4));
        Assert.assertEquals(4, bsearchNumLessThan(new int[]{1, 2, 2, 4, 4, 5, 6}, 5));
        Assert.assertEquals(2, bsearchNumLessThan(new int[]{1, 2, 2, 4, 4, 5, 6}, 4));
        Assert.assertEquals(4, bsearchNumLessThan(new int[]{1, 2, 2, 3, 4, 5, 6}, 5));
        Assert.assertEquals(-1, bsearchNumLessThan(new int[]{1, 2, 2, 3, 4, 5, 6}, 1));
    }
}

