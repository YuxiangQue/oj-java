package com.placeholder.leetcode;

public class _526BeautifulArrangement {

    public static void dfs(boolean[][] candidates, boolean[] used, int index, int[] count) {
        if (index >= candidates.length) {
            count[0] += 1;
            return;
        }
        for (int i = 0; i < candidates[index].length; i++) {
            boolean cand = candidates[index][i];
            if (cand && !used[i]) {
                used[i] = true;
                dfs(candidates, used, index + 1, count);
                used[i] = false;
            }
        }
    }

    public static int countArrangement(int N) {
        boolean[][] candidates = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (j % i == 0 || i % j == 0) {
                    candidates[i][j] = true;
                }
            }
        }
        boolean[] used = new boolean[N + 1];
        int[] count = new int[1];
        dfs(candidates, used, 1, count);
        return count[0];
    }

    public static void main(String[] args) {
        for (int i = 2; i <= 15; ++i) {
            System.out.println(countArrangement(i));
        }
    }
}
