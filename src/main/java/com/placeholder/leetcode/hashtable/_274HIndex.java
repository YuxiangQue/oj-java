package com.placeholder.leetcode.hashtable;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/h-index/
 *
 * @author 阙宇翔
 * @version 2016/2/16
 */
public class _274HIndex {
    public static int hIndex(int[] citations) {
        int index = -1;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int tmp = citations[i] < i ? citations[i] : i;
            index = index > tmp ? index : tmp;
        }
        return index;
    }

    /**
     * https://en.wikipedia.org/wiki/H-index
     * http://www.thinksaas.cn/group/topic/395874/
     * <p>
     * 10 8 5 4 3
     * 0 0 0 0 1
     * 0 0 0 0 2
     * 0 0 0 0 3
     * 0 0 0 1 3
     * 0 0 1 1 3
     *
     * @param citations
     * @return
     */
    public static int hIndex1(int[] citations) {
        int length = citations.length;
        if (length == 0)
            return 0;
        int[] papers = new int[length + 1];

        // 填充 citations->papers 数组
        for (int citation : citations) {
            if (citation >= length)
                papers[length] += 1;
            else
                papers[citation] += 1;
        }

        if (papers[length] >= length)
            return length;

        for (int index = length - 1; index >= 0; --index) {
            papers[index] = papers[index] + papers[index + 1];
            if (papers[index] >= index)
                return index;
        }
        return 0;
    }

    // 0 0 0 1 1 3

    public static void main(String[] args) {
        System.out.println(hIndex1(new int[]{10, 8, 5, 4, 3}));
        System.out.println(hIndex1(new int[]{0, 0}));
    }
}
