package com.placeholder.hihoCoder.weekly;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho115/problem/1
 * http://www.acmerblog.com/ford-fulkerson-6135.html
 */
public class _115MaxFlow {

    public static boolean hasPath(long[][] rGraph, int s, int t, int[] path) {
        int n = rGraph.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next = 0; next < n; next++) {
                if (!visited[next] && rGraph[current][next] > 0) {
                    queue.add(next);
                    visited[next] = true;
                    path[next] = current;
                }
            }
        }
        return visited[t];
    }

    private static long maxFlow(long[][] graph, int s, int t) {
        int n = graph.length;
        long[][] rGraph = new long[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, rGraph[i], 0, n);
        }
        long maxFlow = 0;
        int[] path = new int[n];
        while (hasPath(rGraph, s, t, path)) {
            long minFlow = Long.MAX_VALUE;
            for (int v = t; v != s; v = path[v]) {
                int u = path[v];
                minFlow = Math.min(minFlow, rGraph[u][v]);
            }
            for (int v = t; v != s; v = path[v]) {
                int u = path[v];
                rGraph[u][v] -= minFlow;
                rGraph[v][u] += minFlow;
            }
            maxFlow += minFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), t = scanner.nextInt();
        long[][] graph = new long[n][n];
        for (int i = 0; i < t; i++) {
            graph[scanner.nextInt() - 1][scanner.nextInt() - 1] = scanner.nextLong();
        }
        System.out.println(maxFlow(graph, 0, n - 1));
    }
}
