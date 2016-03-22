package com.placeholder.hihoCoder;


/**
 * @author 阙宇翔
 * @version 2016/3/11
 */
public class Boxing {
    public static void main(String[] args) {
        int i = 0;
        Integer j = new Integer(0);
        System.out.println(i == j);
        System.out.println(j.equals(i));

        Integer s = 9;
        Integer t = 9;
        Long u = new Long(9);

        int arr[] = new int[10];
        //  System.out.println((s == u));
        System.out.println((s == t));
        System.out.println(s.equals(t));
        System.out.println(s.equals(9));
        System.out.println(s.equals(new Integer(9)));


        Object[] arr1 = new String[50];
    }
}
