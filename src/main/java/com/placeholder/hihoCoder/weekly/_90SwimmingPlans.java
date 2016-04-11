package com.placeholder.hihoCoder.weekly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * http://hihocoder.com/contest/hiho90/problem/1 Swimming Plans
 *
 * @author yuxiangque
 * @version 2016/4/10
 */
public class _90SwimmingPlans {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int TASKS = scanner.nextInt();
            for (int i = 0; i < TASKS; i++) {
                int T = scanner.nextInt();
                int L = scanner.nextInt();
                int R = scanner.nextInt();
                int N = scanner.nextInt();
                int Q = scanner.nextInt();
                Plan[] plans = new Plan[Q];
                for (int j = 0; j < Q; j++) {
                    int t = scanner.nextInt();
                    int l = scanner.nextInt();
                    int n = scanner.nextInt();
                    int d = scanner.nextInt();
                    plans[j] = new Plan(t, l, n, d);
                }
                System.out.println(swimmingPlans(T, L, R, N, plans));
            }
        }
    }

    /**
     * @param T     Steven arrives at time T
     * @param L     spending exactly L units of time
     * @param R     swim for R rounds
     * @param N     N parallel lanes
     * @param plans gets Q plans of the other swimmers.
     * @return The earliest time that Steven can finish his swimming plans.
     */
    public static int swimmingPlans(int T, int L, int R, int N, Plan[] plans) {
        int time = T;
        int direction = 0;
        int[] indexes = new int[]{0, 0};
        List<Event>[] eventSequence = new List[]{new ArrayList<>(), new ArrayList<>()};
        for (Plan plan : plans) {
            addEvent(T, L, R, N, plan, eventSequence);
        }

        int[][] lane = new int[2][N];

        while (R > 0) {
            boolean found = false;
            // 循环处理的事件向量
            while (indexes[direction] < 2 * N) {
                Event event = eventSequence[direction].get(indexes[direction]);
                if (event.t < time) {
                    // 当前时间已经经过了这个事件向量的时间
                    // 因此修改对应'x'的值
                    lane[direction][event.n] += 1;
                    indexes[direction] += 1;
                } else {
                    // 当前时间未超过这时间向量
                    // 首先查看在T~event.t这段期间内能不能找到可以使用的泳道
                    // 直接检查lane
                    for (int i = 0; i < N - 1; i++) {
                        if (lane[direction][i] == 0) {
                            // 有可以使用的泳道
                            found = true;
                            // 使用该泳道并增加时间
                            time += L;
                            break;
                        }
                    }
                    if (found) {
                        // 若找到泳道，则可以跳出当次循环，保留当前event
                        break;
                    }
                    // 此时表示在T~event.t这段时间内均没有可以使用的泳道
                    // 则我们直接将时间T推移至event.t，并执行这个事件向量
                    time = event.t;
                    lane[direction][event.n] += event.x;
                    indexes[direction] += 1;
                }
            } // end while
            if (!found) {
                // 会出现found为false的情况，则表明该方向所有的event都已经结束
                // 此时只有小Hi一个人还在游泳
                time += L;
            }
            direction = 1 - direction;
            R -= 1;
        } // end while
        return time - 1;
    }

    // t表示游泳者的出发时间
    // l表示游泳者游泳的持续时间
    // d表示游泳者的方向，只能为0或1
    // n表示游泳者所选取的泳道
    private static void addEvent(int T, int L, int R, int N, Plan plan, List<Event>[] eventSequence) {
        int t = plan.t;
        int l = plan.l;
        int d = plan.d;
        int n = plan.n;


        if (l < L) {
            eventSequence[d].add(new Event(t + l - L, n, 1)); // 同时到达对岸
            eventSequence[d].add(new Event(t, n, -1)); // 同时出发，因为小Hi速度慢，追不上游泳者
        } else if (l == L) {
            eventSequence[d].add(new Event(t + 1, n, 1)); // 晚1个单位时间出发
            eventSequence[d].add(new Event(t - 1, n, -1)); // 早1个单位时间出发
        } else if (l > L) {
            eventSequence[d].add(new Event(t, n, 1)); // 同时出发，因为小Hi速度快，游泳者追不上
            eventSequence[d].add(new Event(t - 1 - L, n, -1)); // 同时到达对岸
        }

        // 异向时，无论如何也得等对面游完
        eventSequence[1 - d].add(new Event(t - L, n, 1)); // 同时出发，因为小Hi速度快，游泳者追不上
        eventSequence[1 - d].add(new Event(t + L, n, -1)); // 游泳者到达后，小Hi出发
    }

    static class Plan {
        int t;  // t - the time he starts
        int l;  // l - the time he spends
        int n;  // n - the lane he chooses
        int d;  //  d - the direction he swims(0 for swimming from west to east and 1 for swimming from east to west)

        public Plan(int t, int l, int n, int d) {
            this.t = t;
            this.l = l;
            this.n = n;
            this.d = d;
        }
    }

    static class Event {
        int t;
        int n;
        int x;

        public Event(int t, int n, int x) {
            this.t = t;
            this.n = n;
            this.x = x;
        }
    }
}
