package com.placeholder.jianzhioffer;

/**
 * @author yuxiangque
 * @version 2016/4/19
 */
public class Power {
    public static double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1.0 / power(base, -exponent);
        }
        return power(base, exponent);
    }

    private static double power(double base, int exponent) {
        if (exponent == 1) {
            return base;
        }
        double temp = Power(base, exponent >> 1);
        temp = temp * temp;
        if ((exponent & 1) == 1) {
            return base * temp;
        } else {
            return temp;
        }
    }

    public static void main(String[] args) {
        System.out.println(Power(11.0, -1));
        System.out.println(Power(11.0, -2));
        System.out.println(Math.pow(11.0, -2.0));
        System.out.println(Power(11.0, 1));
        System.out.println(Power(11.0, 2));
        System.out.println(Power(11.0, 3));
        System.out.println(Power(11.0, 4));
        System.out.println(Power(11.0, 5));
    }
}
