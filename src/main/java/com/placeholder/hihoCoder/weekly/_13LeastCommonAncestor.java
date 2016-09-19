package com.placeholder.hihoCoder.weekly;


import java.util.*;

/**
 * 最近公共祖先
 */
public class _13LeastCommonAncestor {

    private static List<String> ancestors(Map<String, String> sonFather, String son) {
        List<String> ancestors = new ArrayList<>();
        String p = son;
        while (sonFather.containsKey(p)) {
            ancestors.add(p);
            p = sonFather.get(p);
        }
        ancestors.add(p);
        return ancestors;
    }

    public static String lca(Map<String, String> sonFather, String son1, String son2) {
        List<String> ancestors1 = ancestors(sonFather, son1);
        String p = son2;
        while (sonFather.containsKey(p)) {
            for (String ancestor : ancestors1) {
                if (Objects.equals(ancestor, p)) {
                    return ancestor;
                }
            }
            p = sonFather.get(p);
        }
        for (String ancestor : ancestors1) {
            if (Objects.equals(ancestor, p)) {
                return ancestor;
            }
        }
        return "-1";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, String> sonFather = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String father = scanner.next(), son = scanner.next();
            sonFather.put(son, father);// father son
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.println(lca(sonFather, scanner.next(), scanner.next()));
        }
    }
}
