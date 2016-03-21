package com.placeholder.jianzhioffer;

/**
 * Created by yuxiangque on 2016/3/18.
 */
public class _9Fibonacci {
    int Fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int ret1 = 0;
        int ret2 = 1;
        for (int i = 3; i <= n; ++i) {
            int tmp = ret2;
            ret2 = ret1 + ret2;
            ret1 = tmp;
        }
        return ret2;
    }
}
