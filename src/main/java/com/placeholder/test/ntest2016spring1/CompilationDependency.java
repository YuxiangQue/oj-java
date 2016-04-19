package com.placeholder.test.ntest2016spring1;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

/**
 * http://hihocoder.com/contest/ntest2016spring1/problem/2
 * http://blog.csdn.net/dm_vincent/article/details/7714519
 *
 * @author yuxiangque
 * @version 2016/4/10
 */
public class CompilationDependency {

    private static List<Vertex> graph(String[] filenames, int[][] dependencies) {
        int length = filenames.length;
        List<Vertex> all = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            Vertex vertex = new Vertex();
            vertex.index = index;
            vertex.filename = filenames[index];
            vertex.next = new HashSet<>();
            for (int dependency : dependencies[index]) {
                if (dependency == index)
                    continue;
                vertex.next.add(dependency);
            }
            vertex.markState = MarkState.Unmarked;
            all.add(vertex);
        }
        return all;
    }

    public static String[] dependency(String[] filenames, int[][] dependencies) {
        List<Vertex> graph = graph(filenames, dependencies);
        List<Vertex> sortedList = new ArrayList<>();
        if (!dfs(graph, sortedList)) {
            return new String[]{"ERROR"};
        }

        sortedList = sort(sortedList);
        String[] result = new String[filenames.length];
        for (int i = 0; i < sortedList.size(); i++) {
            Vertex node = sortedList.get(i);
            result[i] = node.filename;
        }
        return result;
    }

    /**
     * https://en.wikipedia.org/wiki/Topological_sorting#Depth-first_search
     *
     * @param graph
     * @param sortedList 逆拓扑序列
     * @return
     */
    private static boolean dfs(List<Vertex> graph, List<Vertex> sortedList) {
        for (Vertex current : graph) {
            if (current.markState == MarkState.Unmarked) {
                if (!dfs(graph, 0, current, sortedList)) {
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
    private static boolean dfs(List<Vertex> graph, int depth, Vertex current, List<Vertex> sortedList) {
        if (current.markState == MarkState.TemporaryMark)
            return false;
        if (current.markState == MarkState.Unmarked) {
            current.markState = MarkState.TemporaryMark;
            for (int index : current.next) {
                if (!dfs(graph, depth - 1, graph.get(index), sortedList)) {
                    return false;
                }
            }
            current.markState = MarkState.PermanentlyMark;
            current.depth = depth;
            sortedList.add(current);
        }
        return true;
    }

    // 字典序
    private static List<Vertex> sort(List<Vertex> list1) {
        List<List<Vertex>> lists = split(list1, vertex -> vertex.depth == 0);
        for (List<Vertex> list : lists) {
            Collections.sort(list, (o1, o2) -> {
                if (o1.depth == o2.depth) {
                    return o1.filename.compareTo(o2.filename);
                } else {

                    return o1.depth - o2.depth;
                }
            });
        }
        return sortedMerge(lists, (o1, o2) -> o1.filename.compareTo(o2.filename));
    }

    private static <T> List<List<T>> split(List<T> list, Predicate<T> predicate) {
        List<List<T>> lists = new LinkedList<>();
        int prev = 0;
        for (int index = 0; index < list.size(); index++) {
            if (predicate.test(list.get(index))) {
                List<T> temp1 = list.subList(prev, index + 1);
                lists.add(temp1);
                prev = index + 1;
            }
        }
        return lists;
    }

    private static <T> List<T> sortedMerge(List<List<T>> lists, Comparator<T> comparator) {
        while (lists.size() > 1) {
            List<T> merged = sortedMerge(lists.get(0), lists.get(1), comparator);
            lists.remove(0);
            lists.remove(0);
            lists.add(0, merged);
        }
        if (lists.size() == 1) {
            return lists.get(0);
        }
        return new ArrayList<>();
    }

    private static <T> List<T> sortedMerge(List<T> list1, List<T> list2, Comparator<T> comparator) {
        List<T> temp = new ArrayList<>();
        int length1 = list1.size();
        int length2 = list2.size();
        int index1 = 0;
        int index2 = 0;
        for (int index = 0; index < length1 + length2; index++) {
            if (index1 >= length1) {
                temp.addAll(list2.subList(index2, length2));
                break;
            }
            if (index2 >= length2) {
                temp.addAll(list1.subList(index1, length1));
                break;
            }
            T elem1 = list1.get(index1);
            T elem2 = list2.get(index2);
            if (comparator.compare(elem1, elem2) < 0) {
                temp.add(elem1);
                ++index1;
            } else {
                temp.add(elem2);
                ++index2;
            }
        }
        return temp;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new String[]{"a.cpp", "b.cpp"},
                dependency(new String[]{"a.cpp", "b.cpp"}, new int[][]{{0}, {1, 0}}));
        Assert.assertArrayEquals(new String[]{"c", "cb"},
                dependency(new String[]{"cb", "c"}, new int[][]{{0}, {0}}));
        Assert.assertArrayEquals(new String[]{"ERROR"},
                dependency(new String[]{"a.cpp", "b.cpp"}, new int[][]{{1, 1}, {1, 0}}));
        Assert.assertArrayEquals(new String[]{"a.cpp", "d.cpp", "e.cpp", "c.cpp", "b.cpp", "f.cpp"},
                dependency(
                        new String[]{"a.cpp", "b.cpp", "c.cpp", "e.cpp", "d.cpp", "f.cpp"},
                        new int[][]{{0}, {1, 2}, {2, 3, 4}, {3}, {4}, {5}}));
    }

    private enum MarkState {
        Unmarked,
        TemporaryMark,
        PermanentlyMark
    }

    private static class Vertex {
        int index;
        Set<Integer> next;
        String filename;
        MarkState markState;
        int depth;
    }
}
