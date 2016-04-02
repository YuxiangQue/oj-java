package com.placeholder.netease2016;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/3/22 网易实习校招Java题
 * 回溯
 *
 * @author yuxiangque
 * @version 2016/3/22
 */
public class Netease2016Campus {

    public static void main(String[] args) {
        char[] candidates = new char[]{'9', 'g', '3', '4', '3', '4', '3', '4', '3', '4', '3', '4', '3', '4', '3', '4'};
        List<List<Character>> result = new ArrayList<>();
        dfs(candidates, 0, new ArrayList<>(), result);
        return;
    }

    static void dfs(char[] candidates, int index, List<Character> item, List<List<Character>> result) {
        if (index == candidates.length) {
            List<Character> item1 = new ArrayList<>();
            item1.addAll(item);
            result.add(item1);
            return;
        }
        char ch = candidates[index];
        if (ch == 'g' || ch == '9') {
            item.add('g');
            dfs(candidates, index + 1, item, result);
            item.remove(item.size() - 1);
            item.add('9');
            dfs(candidates, index + 1, item, result);
            item.remove(item.size() - 1);
        } else {
            item.add(ch);
            dfs(candidates, index + 1, item, result);
            item.remove(item.size() - 1);
        }
    }
}
