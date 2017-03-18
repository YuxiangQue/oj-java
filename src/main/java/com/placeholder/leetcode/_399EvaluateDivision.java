package com.placeholder.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _399EvaluateDivision {

    public static void main(String[] args) {
        double[] result = calcEquation(
                new String[][]{{"a", "b"}, {"b", "c"}, {"bc", "cd"}},
                new double[]{1.5, 2.5, 5.0},
                new String[][]{{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}});
        System.out.println(Arrays.toString(result));
    }

    static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> quot = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String num = equations[i][0], den = equations[i][1];
            double value = values[i];
            if (!quot.containsKey(num)) {
                quot.put(num, new HashMap<>());
            }
            if (!quot.containsKey(den)) {
                quot.put(den, new HashMap<>());
            }
            quot.get(num).put(num, 1.0);
            quot.get(den).put(den, 1.0);
            quot.get(num).put(den, value);
            quot.get(den).put(num, 1.0 / value);
        }
        for (String k : quot.keySet()) {
            for (String i : quot.get(k).keySet()) {
                for (String j : quot.get(k).keySet()) {
                    quot.get(i).put(j, quot.get(i).get(k) * quot.get(k).get(j));
                }
            }
        }
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String num = queries[i][0], den = queries[i][1];
            result[i] = -1.0;
            if (quot.containsKey(num)) {
                result[i] = quot.get(num).getOrDefault(den, -1.0);
            }
        }
        return result;
    }

    static class Edge {
        int vertex;
        double weight;
    }
}
