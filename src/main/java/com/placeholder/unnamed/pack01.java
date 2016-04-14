package com.placeholder.unnamed;

/**
 * http://love-oriented.com/pack/
 * http://blog.csdn.net/insistgogo/article/details/8579597
 *
 * @author yuxiangque
 * @version 2016/4/2
 */
public class Pack01 {

    // if j > wi then
    //  m[i][j] = max(m[i+1][j], m[i+1][j-wi] + vi)
    // else
    //  m[i][j] = m[i+1][j]

    // if j > wn then
    //  m[n][j] = vn
    // else
    //  0

    /**
     * @param capacity 容量为capacity的背包
     * @param weight   第i件物品的价值是weight[i]
     * @param value    第i件物品的费用是value[i]
     */
    static int pack01(int capacity, int[] weight, int[] value) {
        int n = weight.length - 1;
        int[][] m = new int[n + 1][capacity + 1];

        // 1..n
        for (int i = 1; i <= n; ++i) { // row
            for (int remainCapacity = 0; remainCapacity <= capacity; remainCapacity++) { // column
                if (remainCapacity < weight[i]) { // 当前位置就不能放置
                    m[i][remainCapacity] = m[i - 1][remainCapacity];
                } else {
                    m[i][remainCapacity] = m[i - 1][remainCapacity] > m[i - 1][remainCapacity - weight[i]] + value[i] ?
                            m[i - 1][remainCapacity] :
                            m[i - 1][remainCapacity - weight[i]] + value[i];
                }
            }
        }

        int max = 0;
        for (int m2 : m[n]) {
            max = m2 > max ? m2 : max;
        }
        return max;
    }

    static int searchPack(int capacity, int[] weight, int[] value, int i, int j) {
        if (i == weight.length) {
            return value[i - 1];
        }
        if (j < weight[i]) {
            return searchPack(capacity, weight, value, i + 1, j);
        } else {
            return Math.max(searchPack(capacity, weight, value, i + 1, j),
                    searchPack(capacity, weight, value, i + 1, j - weight[i]));
        }
    }


    public static void main(String[] args) {
        System.out.println(pack01(10, new int[]{0, 3, 4, 5}, new int[]{0, 4, 5, 6}));

        int max = searchPack(10, new int[]{0, 3, 4, 5}, new int[]{0, 4, 5, 6}, 0, 0);
        System.out.println(max);
    }
}
