package com.placeholder.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * http://blog.csdn.net/linhuanmars/article/details/22028697
 *
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _60PermutationSequence {
    public static void main(String[] args) {
        System.out.println(new NQueens().getPermutation(9, 278082));
        System.out.println(new Fac().getPermutation(9, 278082));
    }

    static class NQueens {

        public String getPermutation(int n, int k) {
            List<List<Integer>> possibleSequences = new ArrayList<>();
            List<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                candidates.add(i + 1);
            }
            dfs(n, k, candidates, new ArrayList<>(), possibleSequences);
            return buildString(possibleSequences.get(possibleSequences.size() - 1));
        }

        /**
         * @param sequence 保存着一序列的数
         */
        private String buildString(List<Integer> sequence) {
            StringBuilder sb = new StringBuilder();
            for (Integer digit : sequence) {
                sb.append(digit);
            }
            return sb.toString();
        }

        /**
         * @param n
         * @param k
         * @param candidates        可用的
         * @param sequence          持有当前组合
         * @param possibleSequences 所有组合
         * @return
         */
        private boolean dfs(int n, int k, List<Integer> candidates, List<Integer> sequence, List<List<Integer>> possibleSequences) {
            if (candidates.size() == 0) {
                possibleSequences.add(new ArrayList<>(sequence));
                return possibleSequences.size() == k;
            }
            int length = candidates.size();
            for (int i = 0; i < length; i++) {
                int tmp = candidates.remove(i);
                sequence.add(tmp);
                if (dfs(n, k, candidates, sequence, possibleSequences)) {
                    return true;
                }
                sequence.remove(sequence.size() - 1);
                candidates.add(i, tmp);
            }
            return false;
        }
    }

    static class Fac {


        public String getPermutation(int n, int k) {
            int[] fac = new int[n + 1];
            fac[0] = fac[1] = 1;
            for (int i = 1; i < n; ++i) {
                fac[i + 1] = (i + 1) * fac[i];
            }
            List<Integer> candidate = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; ++i) {
                candidate.add(i + 1);
            }
            --k;
            for (int i = n - 1; i >= 0; --i) {
                int index = k / fac[i];
                k %= fac[i];
                sb.append(candidate.get(index));
                candidate.remove(index);
            }
            return sb.toString();
        }

        /**
         * @param sequence 保存着一序列的数
         */
        private String buildString(List<Integer> sequence) {
            StringBuilder sb = new StringBuilder();
            for (Integer digit : sequence) {
                sb.append(digit);
            }
            return sb.toString();
        }


    }
}
