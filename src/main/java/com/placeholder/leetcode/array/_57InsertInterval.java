package com.placeholder.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _57InsertInterval {
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        intervals.add(newInterval);
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        int i = 0;
        int j = 1;
        while (j < intervals.size()) {
            Interval a = result.get(i);
            Interval b = intervals.get(j);
            if (b.start <= a.end) {
                if (a.end < b.end) {
                    a.end = b.end;
                }
                j += 1;
            } else {
                result.add(intervals.get(j));
                i += 1;
                j += 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(insert(new ArrayList<>(), new Interval(1, 3)));

        List<Interval> single0 = new ArrayList<>();
        single0.add(new Interval(1, 3));
        System.out.println(insert(single0, new Interval(2, 4)));

        List<Interval> single1 = new ArrayList<>();
        single1.add(new Interval(1, 3));
        System.out.println(insert(single1, new Interval(0, 2)));

        List<Interval> many0 = new ArrayList<>();
        many0.add(new Interval(1, 3));
        many0.add(new Interval(2, 6));
        many0.add(new Interval(8, 10));
        many0.add(new Interval(15, 18));
        System.out.println(insert(many0, new Interval(2, 5)));

        List<Interval> many1 = new ArrayList<>();
        many1.add(new Interval(2, 6));
        many1.add(new Interval(1, 3));
        many1.add(new Interval(8, 10));
        many1.add(new Interval(15, 18));
        System.out.println(insert(many1, new Interval(1, 3)));

        List<Interval> many2 = new ArrayList<>();
        many2.add(new Interval(1, 4));
        many2.add(new Interval(2, 3));
        System.out.println(insert(many2, new Interval(1, 3)));
    }

    static class Interval {
        int start;
        int end;

        Interval() {
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
