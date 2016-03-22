package com.placeholder.jianzhioffer;

/**
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _JumpFloor2 {
    int jumpFloorII(int number) {
        int[] tmp = new int[number + 1];
        tmp[1] = 1;
        tmp[2] = 2;
        for (int i = 3; i <= number; ++i) {
            tmp[i] = 1;
            for (int j = 1; j < i; ++j) {
                tmp[i] += tmp[j];
            }
        }
        int ret = tmp[number];
        return ret;
    }
}
