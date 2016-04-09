package com.placeholder.ntest2016spring1;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * http://hihocoder.com/contest/ntest2016spring1/problem/2
 * http://blog.csdn.net/dm_vincent/article/details/7714519
 *
 * @author yuxiangque
 * @version 2016/4/10
 */
public class CompilationDependency {

    private List<CompilationNode> graph(String[] filenames, int[][] dependencies) {
        int length = filenames.length;
        List<CompilationNode> all = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            CompilationNode compilationNode = new CompilationNode();
            compilationNode.me = index;
            compilationNode.filename = filenames[index];
            compilationNode.next = new HashSet<>();
            for (int dependency : dependencies[index]) {
                if (dependency == index)
                    continue;
                compilationNode.next.add(dependency);
            }
            compilationNode.markState = MarkState.Unmarked;
            all.add(compilationNode);
        }
        return all;
    }

    public String[] dependency(String[] filenames, int[][] dependencies) {
        List<CompilationNode> graph = graph(filenames, dependencies);
        List<CompilationNode> sortedList = new ArrayList<>();
        if (!topologicalSort(graph, sortedList)) {
            return new String[]{"ERROR"};
        }
        String[] result = new String[sortedList.size()];
        for (int index = 0; index < sortedList.size(); index++) {
            result[index] = sortedList.get(index).filename;
        }
        Arrays.sort(result);
        return result;
    }

    /**
     * https://en.wikipedia.org/wiki/Topological_sorting#Depth-first_search
     *
     * @param graph
     * @param sortedList
     * @return
     */
    private boolean topologicalSort(List<CompilationNode> graph, List<CompilationNode> sortedList) {
        for (CompilationNode current : graph) {
            if (current.markState == MarkState.Unmarked) {
                if (!dfs(graph, current, sortedList)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param graph
     * @param current
     * @param sortedList
     * @return return false if not a DAG
     */
    private boolean dfs(List<CompilationNode> graph, CompilationNode current, List<CompilationNode> sortedList) {
        if (current.markState == MarkState.TemporaryMark)
            return false;
        if (current.markState == MarkState.Unmarked) {
            current.markState = MarkState.TemporaryMark;
            for (int index : current.next) {
                if (!dfs(graph, graph.get(index), sortedList)) {
                    return false;
                }
            }
            current.markState = MarkState.PermanentlyMark;
            sortedList.add(current);
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new String[]{"a.cpp", "b.cpp"},
                dependency(new String[]{"a.cpp", "b.cpp"}, new int[][]{{0}, {1, 0}}));
        Assert.assertArrayEquals(new String[]{"c", "cb"},
                dependency(new String[]{"cb", "c"}, new int[][]{{0}, {0}}));
        Assert.assertArrayEquals(new String[]{"ERROR"},
                dependency(new String[]{"a.cpp", "b.cpp"}, new int[][]{{1, 1}, {1, 0}}));
    }

    enum MarkState {
        Unmarked,
        TemporaryMark,
        PermanentlyMark
    }

    private static class CompilationNode {
        int me;
        Set<Integer> next;
        String filename;
        MarkState markState;
    }
}
