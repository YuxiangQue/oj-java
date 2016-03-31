package com.placeholder;

/**
 * @author yuxiangque
 * @version 2016/3/31
 */
public class InvokeVirtualMethodInBaseClass {

    public static void main(String[] args) {
        ClassB b = new ClassB();
        return;
    }

    static class ClassA {
        private String classAName = "A";

        public ClassA() {
            print1();
        }

        public void print1() {
            System.out.println(classAName);
        }
    }

    static class ClassB extends ClassA {
        private String classAName = "ClassB";

        // 此时子类B还没有虚拟化
        public void print1() {
            System.out.println(classAName);
        }
    }
}
