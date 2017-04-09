package com.placeholder.hihoCoder.weekly;

import java.util.Scanner;

public class _145dp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        for (int j = 0; j < Q; j++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int S = scanner.nextInt();
            int T = scanner.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = scanner.nextInt();
            }
            Solver solver = new Solver(N, M, S, T, A);
            int n = solver.solve();
            System.out.println(n != Integer.MAX_VALUE ? n : "No");
        }
    }

    static class Record {
        int level; // 关卡
        int score; // 得分

        public Record(int level, int score) {
            this.level = level;
            this.score = score;
        }
    }

    static class Solver {
        int N;
        int M;
        int S;
        int T;
        int[] A;

        public Solver(int n, int m, int s, int t, int[] a) {
            N = n;
            M = m;
            S = s;
            T = t;
            A = a;
        }

        public Record max(Record a, Record b) {
            if (a.level == b.level) {
                return a.score >= b.score ? a : b;
            } else {
                return a.level > b.level ? a : b;
            }
        }

        public Record negative(Record a) {
            int x = a.level, y = a.score;
            if (x < N && y + T >= A[x]) {
                return new Record(x + 1, 0);
            } else {
                return new Record(x, y + T);
            }
        }

        public Record positive(Record a) {
            int x = a.level, y = a.score;
            if (x < N && y + S >= A[x]) {
                return new Record(x + 1, 0);
            } else {
                return new Record(x, y + S);
            }
        }

        public int solve() {
            Record[][] dp = new Record[M + 1][M + 1];

            dp[0][0] = new Record(0, 0);

            for (int i = 1; i <= M; ++i) {
                dp[i][0] = positive(dp[i - 1][0]);
            }

            for (int j = 1; j <= M; ++j) {
                dp[0][j] = negative(dp[0][j - 1]);
            }

            for (int i = 1; i <= M; ++i) {
                for (int j = 1; i + j <= M; ++j) {
                    dp[i][j] = max(positive(dp[i - 1][j]), negative(dp[i][j - 1]));
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i <= M; i++) {
                for (int j = 0; i + j <= M; ++j) {
                    if (dp[i][j].level >= N) {
                        min = Math.min(min, i);
                    }
                }
            }
            return min;
        }
    }
}
