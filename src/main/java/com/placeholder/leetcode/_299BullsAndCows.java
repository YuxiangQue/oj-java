package com.placeholder.leetcode;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * https://leetcode.com/problems/bulls-and-cows/
 *
 * @author 阙宇翔
 * @version 2016/2/15
 */
public class _299BullsAndCows {

    public static String getHint1(String secret, String guess) {
        int length = secret.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < length; ++i) {
            Character secretChar = secret.charAt(i);
            Character guessChar = guess.charAt(i);

            if (secretChar == guessChar) {
                ++bulls;
            } else {
                int secretCount = map.getOrDefault(secretChar, 0);
                int guessCount = map.getOrDefault(guessChar, 0);

                if (secretCount < 0)   // #2, 小于0说明猜对了但是位置不对
                    ++cows;
                if (guessCount > 0)    // #1, 大于0说明猜对了但是位置不对
                    ++cows;

                map.put(secretChar, secretCount + 1); // #1
                map.put(guessChar, guessCount - 1);   // #2
            }
        }
        return String.format("%dA%dB", bulls, cows);
    }


    public static String getHint(String secret, String guess) {
        int length = secret.length();
        HashMap<Character, Integer> secretMap = new HashMap<>();
        HashMap<Character, Integer> guessMap = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < length; ++i) {
            Character secretChar = secret.charAt(i);
            Character guessChar = guess.charAt(i);

            int secretCount = secretMap.getOrDefault(secretChar, 0);
            int guessCount = guessMap.getOrDefault(guessChar, 0);

            secretMap.put(secretChar, secretCount + 1);
            guessMap.put(guessChar, guessCount + 1);

            if (secretChar == guessChar) {
                ++bulls;
            }
        }
        Set<Character> set = secretMap.keySet();
        for (Character secretChar : set) {
            int secretCount = secretMap.getOrDefault(secretChar, 0);
            int guessCount = guessMap.getOrDefault(secretChar, 0);
            cows += guessCount < secretCount ? guessCount : secretCount;
        }
        cows -= bulls;
        return String.format("%dA%dB", bulls, cows);
    }

    public static void main(String[] args) {
        System.out.println(Objects.equals(getHint1("1123", "0111"), "1A1B"));
        System.out.println(Objects.equals(getHint1("1807", "7810"), "1A3B"));
    }
}
