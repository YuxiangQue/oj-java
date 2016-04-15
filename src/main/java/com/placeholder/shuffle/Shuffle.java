package com.placeholder.shuffle;

/**
 * @author yuxiangque
 * @version 2016/4/15
 */
public class Shuffle {
    int rand(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }

    public void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int k = rand(0, i);
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }

    public int[] randomPickK(int[] arr, int k) {
        int[] picked = new int[k];
        System.arraycopy(arr, 0, picked, 0, k);
        for (int i = k; i < arr.length; i++) {
            int m = rand(0, i);
            if (k < m) {  // 以k/(i+1)概率决定是否要把它换入
                picked[m] = arr[i];
            }
        }
        return picked;
    }
}
