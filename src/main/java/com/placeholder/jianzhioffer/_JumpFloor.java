package com.placeholder.jianzhioffer;

/**
 * @author 阙宇翔
 * @version 2016/3/18
 */
public class _JumpFloor {
    int jumpFloor(int number) {
        if (number == 1)
            return 1;
        if (number == 2)
            return 2;
        int a = 1;
        int b = 2;
        for (int i = 3; i <= number; ++i) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }
}
