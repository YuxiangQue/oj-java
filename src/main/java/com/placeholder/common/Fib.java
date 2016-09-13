package com.placeholder.common;

class Fib {

    public static int fib1(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    public static int fib2(int n) {
        if (n == 0)
            return 0;
        int ret1 = 0;
        int ret2 = 1;
        for (int i = 1; i < n; ++i) { // n-1次
            int temp = ret1 + ret2;
            ret2 = ret1;
            ret1 = temp;
        }
        return ret2;
    }

    // f(0, 1, 0) = 0
    // f(1, 1, 0) = f(0, 1, 1) = 1
    // f(2, 1, 0) = f(1, 1, 1) = f(0, 2, 1) = 1
    // f(3, 1, 0) = f(2, 1, 1) = f(1, 2, 1) = f(0, 3, 2) = 2
    public static int fib3(int n, int ret1, int ret2) {
        if (n == 0)
            return ret2;
        return fib3(n - 1, ret1 + ret2, ret1);
    }

    // F(n)   F(n-1) = 1 1
    // F(n-1) F(n-2)   1 0
    static long fib(int n) {
        if (n == 0 || n == 1) return n;
        Matrix22 m = new Matrix22(1, 1, 1, 0);
        return Matrix22.power(m, n - 1).m00;
    }

    public static void main(String[] args) {
        Matrix22 m = new Matrix22(1, 1, 1, 0);
        System.out.println(Matrix22.power(m, 1));
        System.out.println(Matrix22.power(m, 2));
        System.out.println(Matrix22.power(m, 3));

        System.out.println(fib(2));
        System.out.println(fib(3));
    }

    // http://www.cnblogs.com/python27/archive/2011/11/25/2261980.html
    static class Matrix22 {
        long m00, m01, m10, m11;

        public Matrix22(long m00, long m01, long m10, long m11) {
            this.m00 = m00;
            this.m01 = m01;
            this.m10 = m10;
            this.m11 = m11;
        }

        public Matrix22() {

        }

        static Matrix22 multiply(Matrix22 a, Matrix22 b) {
            Matrix22 c = new Matrix22();
            c.m00 = a.m00 * b.m00 + a.m01 * b.m10;
            c.m01 = a.m00 * b.m01 + a.m01 * b.m11;
            c.m10 = a.m10 * b.m00 + a.m11 * b.m10;
            c.m11 = a.m10 * b.m01 + a.m11 * b.m11;
            return c;
        }

        static Matrix22 power(Matrix22 a, int n) {
            Matrix22 b = null;
            if (n == 1) {
                b = a;
            } else if (n % 2 == 0) {
                Matrix22 temp = power(a, n / 2);
                b = multiply(temp, temp);
            } else if (n % 2 == 1) {
                Matrix22 temp = power(a, n / 2);
                b = multiply(a, multiply(temp, temp));
            }
            return b;
        }

        @Override
        public String toString() {
            return
                    "[" + m00 +
                            "," + m01 +
                            "]\r\n[" + m10 +
                            "," + m11 +
                            ']';
        }
    }
}