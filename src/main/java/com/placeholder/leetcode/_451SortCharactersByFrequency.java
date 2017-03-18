package com.placeholder.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class _451SortCharactersByFrequency {
    public static void main(String[] args) {
        System.out.println(frequencySort("Aabb"));
        System.out.println(frequencySort("tree"));
    }

    public static String frequencySort(String s) {
        int[] frequency = new int[256];
        for (int i = 0; i < s.length(); ++i) {
            frequency[s.charAt(i)] += 1;
        }

        TreeMap<Integer, StringBuilder> frequencyToChar = new TreeMap<>();
        for (int i = 0; i < 256; ++i) {
            if (frequency[i] != 0)
                frequencyToChar.put(frequency[i], frequencyToChar.getOrDefault(frequency[i], new StringBuilder()).append((char) i));
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StringBuilder> entry : frequencyToChar.descendingMap().entrySet()) {
            String chars = entry.getValue().toString();
            for (int i = 0; i < chars.length(); i++) {
                char ch = chars.charAt(i);
                for (int j = 0; j < entry.getKey(); ++j) {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
}
