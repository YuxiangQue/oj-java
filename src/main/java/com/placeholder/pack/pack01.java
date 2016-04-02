package com.placeholder.pack;

/**
 * http://love-oriented.com/pack/
 *
 * @author yuxiangque
 * @version 2016/4/2
 */
public class pack01 {

    // if j > wi then
    //  m[i][j] = max(m[i+1][j], m[i+1][j-wi] + vi)
    // else
    //  m[i][j] = m[i+1][j]

    // if j > wn then
    //  m[n][j] = vn
    // else
    //  0
    static void pack01(int capacity, int[] weight, int[] value) {
        int n = weight.length - 1;
        int[][] m = new int[n + 1][capacity + 1];

        // w[n]
        for (int j = 0; j <= capacity; ++j) {
            if (j > weight[n])
                m[n][j] = value[n];
        }

        // w[n-1] .. w[1]
        for (int i = n - 1; i >= 1; --i) { // row
            for (int j = 0; j <= capacity; j++) { // column
                if (j < weight[i]) { // 当前位置就不能放置
                    m[i][j] = m[i + 1][j];
                } else {
                    m[i][j] = m[i + 1][j] > m[i + 1][j - weight[i]] + value[i] ?
                            m[i + 1][j] : m[i + 1][j - weight[i]] + value[i];
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        pack01(10, new int[]{0, 3, 4, 5}, new int[]{0, 4, 5, 6});
    }
}
