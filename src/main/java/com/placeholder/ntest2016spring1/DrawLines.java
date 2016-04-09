package com.placeholder.ntest2016spring1;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * http://hihocoder.com/contest/ntest2016spring1/problem/3
 *
 * @author yuxiangque
 * @version 2016/4/10
 */
public class DrawLines {

    private static void mergePositive45(List<Line> lines) {
        Collections.sort(lines, (o1, o2) -> o1.x0 - o2.x0);
        for (int index = 0; index < lines.size() - 1; index++) {
            Line l0 = lines.get(index);
            Line l1 = lines.get(index + 1);
            if (l0.x1 >= l1.x1) {
                l1.x0 = l0.x0;
                l1.y0 = l0.y0;
                l1.x1 = l0.x1;
                l1.y1 = l0.y1;
                lines.set(index, null);
            }
            if (l0.x1 >= l1.x0) {
                l1.x0 = l0.x0;
                l1.y0 = l0.y0;
                lines.set(index, null);
            }
        }
        lines.removeIf((any) -> any == null);
    }

    private static void mergeNegative45(List<Line> lines) {
        Collections.sort(lines, (o1, o2) -> o1.x0 - o2.x0);
        for (int index = 0; index < lines.size() - 1; index++) {
            Line l0 = lines.get(index);
            Line l1 = lines.get(index + 1);
            if (l0.x1 >= l1.x1) {
                l1.x0 = l0.x0;
                l1.y0 = l0.y0;
                l1.x1 = l0.x1;
                l1.y1 = l0.y1;
                lines.set(index, null);
            }
            if (l0.x1 >= l1.x0) {
                l1.x0 = l0.x0;
                l1.y0 = l0.y0;
                lines.set(index, null);
            }
        }
        lines.removeIf((any) -> any == null);
    }

    private static void mergeVertical(List<Line> lines) {
        Collections.sort(lines, (o1, o2) -> o1.x0 - o2.x0);
        for (int index = 0; index < lines.size() - 1; index++) {
            Line l0 = lines.get(index);
            Line l1 = lines.get(index + 1);
            if (l0.x1 >= l1.x1) {
                l1.x0 = l0.x0;
                l1.x1 = l0.x1;
                lines.set(index, null);
            }
            if (l0.x1 >= l1.x0) {
                l1.x0 = l0.x0;
                lines.set(index, null);
            }
        }
        lines.removeIf((any) -> any == null);
    }

    private static void mergeHorizontal(List<Line> lines) {
        Collections.sort(lines, (o1, o2) -> o1.y0 - o2.y1);
        for (int index = 0; index < lines.size() - 1; index++) {
            Line l0 = lines.get(index);
            Line l1 = lines.get(index + 1);
            if (l0.y1 >= l1.y1) {
                l1.y0 = l0.y0;
                l1.y1 = l0.y1;
                lines.set(index, null);
            }
            if (l0.y1 >= l1.y0) {
                l1.y0 = l0.y0;
                lines.set(index, null);
            }
        }
        lines.removeIf((any) -> any == null);
    }

    @Test
    public void test() {
        List<Line> lines = new ArrayList<>();

        //
        lines.add(new Line(3, 8, 6, 8));

        // -7 8 1 8
        lines.add(new Line(-3, 8, 1, 8));
        lines.add(new Line(-4, 8, -3, 8));
        lines.add(new Line(-7, 8, -4, 8));

        // 1 2 1 11
        lines.add(new Line(1, 2, 1, 10));
        lines.add(new Line(1, 3, 1, 11));


        // 2 3 11 12
        lines.add(new Line(2, 3, 4, 5));
        lines.add(new Line(3, 4, 6, 7));
        lines.add(new Line(6, 7, 11, 12));

        // 0 1 3 -2
        lines.add(new Line(0, 1, 1, 0));
        lines.add(new Line(1, 0, 2, -1));
        lines.add(new Line(2, -1, 3, -2));


        Assert.assertEquals(5, drawLines(lines));


        lines = new ArrayList<>();
        lines.add(new Line(1, 1, 2, 2));
        lines.add(new Line(2, 2, 3, 3));
        lines.add(new Line(3, 3, 4, 2));
        lines.add(new Line(4, 2, 5, 1));
        lines.add(new Line(1, 0, 100, 0));
        Assert.assertEquals(3, drawLines(lines));
    }

    private int drawLines(List<Line> lines) {

        // group
        //
        Map<Integer, List<Line>> horizontal = new HashMap<>();      // 水平线段
        Map<Integer, List<Line>> vertical = new HashMap<>();        // 垂直线段
        Map<Integer, List<Line>> positive45 = new HashMap<>();      // 正45度
        Map<Integer, List<Line>> negative45 = new HashMap<>();      // 反45度
        for (Line line : lines) {
            if (line.y0 == line.y1) {                               // 垂直线段
                int key = line.y0;
                if (!vertical.containsKey(key)) {
                    vertical.put(key, new ArrayList<>());
                }
                vertical.get(key).add(line);
            } else if (line.x0 == line.x1) {                        // 水平线段
                int key = line.x0;
                if (!horizontal.containsKey(key)) {
                    horizontal.put(key, new ArrayList<Line>());
                }
                horizontal.get(key).add(line);
            } else if (line.y1 - line.y0 == line.x1 - line.x0) {    // 正45度
                int key = line.x0 - line.y0;
                if (!positive45.containsKey(key)) {
                    positive45.put(key, new ArrayList<Line>());
                }
                positive45.get(key).add(line);
            } else if (line.y1 - line.y0 == -line.x1 + line.x0) {    // 反45度
                int key = line.x0 + line.y0;
                if (!negative45.containsKey(key)) {
                    negative45.put(key, new ArrayList<Line>());
                }
                negative45.get(key).add(line);
            }
        }

        // merge
        //
        int count = 0;
        for (List<Line> temp : vertical.values()) {
            mergeVertical(temp);
            count += temp.size();
        }
        for (List<Line> temp : horizontal.values()) {
            mergeHorizontal(temp);
            count += temp.size();
        }
        for (List<Line> temp : positive45.values()) {
            mergePositive45(temp);
            count += temp.size();
        }
        for (List<Line> temp : negative45.values()) {
            mergeNegative45(temp);
            count += temp.size();
        }
        return count;
    }

    private static class Line {
        int x0;
        int y0;
        int x1;
        int y1;

        public Line(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
    }
}
