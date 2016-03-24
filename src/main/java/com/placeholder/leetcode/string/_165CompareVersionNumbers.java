package com.placeholder.leetcode.string;

/**
 * @author yuxiangque
 * @version 2016/3/24
 */
public class _165CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] versions1 = version1.split(".");  // skip empty
        String[] versions2 = version2.split(".");
        int version1Length = versions1.length;
        int version2Length = versions2.length;
        for (int i = 0; i < version1Length || i < version2Length; ++i) {
            int num1 = 0;
            int num2 = 0;
            if (i < version1Length) {
                num1 = Integer.parseInt(versions1[i]);
            }
            if (i < version2Length) {
                num2 = Integer.parseInt(versions1[i]);
            }
            if (num1 > num2)
                return 1;
            else if (num1 < num2)
                return -1;
        }
        return 0;
    }
}
