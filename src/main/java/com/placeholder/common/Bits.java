package com.placeholder.common;

/**
 * bit位操作
 *
 * @author yuxiangque
 * @version 2016/4/4
 */
public class Bits {

    // 获取
    static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    // 置位
    static int setBit(int num, int i) {
        return num | (1 << i);
    }

    // 清零
    static int clearBit(int num, int i) {
        return num & (~(1 << i));
    }

    // 更新
    static int updateBit(int num, int i, int v) {
        num = num & (~(1 << i)); // clear
        return num | (v << i);   // set
    }

    // num最高位至第i位（包括）清零
    static int clearBitsMSBthourhI(int num, int i) {
        return num & ((1 << i) - 1);
    }

    // num第i位（包括）至0位清零
    static int clearBitsIthrough0(int num, int i) {
        return num & (~((1 << i) - 1));
    }
}
