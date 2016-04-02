package com.placeholder.mogujie2016;

import java.util.*;

/**
 * 倒水问题，2016蘑菇街
 *
 * @author yuxiangque
 * @version 2016/3/31
 */
public class Water {


    public static int bfs(State begin, State end) {
        Set<State> visited = new HashSet<>();
        Queue<State> states = new ArrayDeque<>();
        Queue<Integer> depths = new ArrayDeque<>();
        states.add(begin);
        depths.add(0);
        visited.add(begin);
        while (states.size() != 0) {
            State current = states.poll();
            int currentDepth = depths.poll();
            visited.add(current);
            if (current.equals(end)) {
                return currentDepth;
            }
            current.nextStates().stream()
                    .filter(nextState -> !visited.contains(nextState))
                    .forEach(nextState -> {
                        depths.add(currentDepth + 1);
                        states.add(nextState);
                    });
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            State begin = new State(new int[]{10, 0, 0, 0});
            int bottom1 = scanner.nextInt();
            int bottom2 = scanner.nextInt();
            int bottom3 = scanner.nextInt();
            int bottom4 = scanner.nextInt();
            State end = new State(new int[]{bottom1, bottom2, bottom3, bottom4});
            System.out.println(bfs(begin, end));
        }
    }

    // 10 6 5 4
    public static class State {
        int[] bottles = new int[4];
        int[] capacity = new int[]{10, 6, 5, 4};

        public State(int[] bottles) {
            this.bottles = Arrays.copyOf(bottles, 4);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (!Arrays.equals(bottles, state.bottles)) return false;
            return Arrays.equals(capacity, state.capacity);

        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(bottles);
            result = 31 * result + Arrays.hashCode(capacity);
            return result;
        }

        public List<State> nextStates() {
            List<State> states = new ArrayList<>();
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    int[] temp = Arrays.copyOf(bottles, 4);
                    if (j == i)
                        continue;
                    // i -> j
                    if (capacity[j] >= temp[i] + temp[j]) {
                        temp[j] += temp[i];
                        temp[i] = 0;
                    } else {
                        int offset = capacity[j] - temp[j];
                        temp[j] += offset;
                        temp[i] -= offset;
                    }
                    states.add(new State(temp));
                }
            }
            return states;
        }
    }
}
