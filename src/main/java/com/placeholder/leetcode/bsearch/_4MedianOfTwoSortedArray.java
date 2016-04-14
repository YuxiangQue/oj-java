package com.placeholder.leetcode.bsearch;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * http://blog.csdn.net/zxzxy1988/article/details/8587244
 * <p>
 * #BinarySearch
 *
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _4MedianOfTwoSortedArray {


    public static void main(String[] args) {

    }

    double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k = (m + n) / 2;
        int num1 = findKth(nums1, 0, m, nums2, 0, n, k + 1);
        if ((n + m) % 2 == 0) {
            int num2 = findKth(nums1, 0, m, nums2, 0, n, k);
            return (num1 + num2) / 2.0;
        } else return num1;
    }

    int findKth(int[] nums1, int left1, int right1, int[] nums2, int left2, int right2, int k) {
        int m = right1 - left1;
        int n = right2 - left2;
        if (m > n)
            return findKth(nums2, left2, right2, nums1, left1, right1, k);
        else if (m == 0)
            return nums2[left2 + k - 1];
        else if (k == 1)
            return Math.min(nums1[left1], nums2[left2]);
        else {
            int count1 = Math.min(k / 2, m);
            int count2 = k - count1;
            if (nums1[left1 + count1 - 1] == nums2[left2 + count2 - 1])
                return nums1[left1 + count1 - 1];
            else if (nums1[left1 + count1 - 1] < nums2[left2 + count2 - 1])
                return findKth(nums1, left1 + count1, right1, nums2, left2, right2, k - count1);
            else
                return findKth(nums1, left1, right1, nums2, left2 + count2, right2, k - count2);
        }
    }
}
