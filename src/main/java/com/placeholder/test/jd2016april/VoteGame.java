package com.placeholder.test.jd2016april;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class VoteGame {
    public static int vote(int votes[]) {
        int numOfVote = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int index = 1; index < votes.length; index++) {
            pq.offer(votes[index]);
        }
        while (votes[0] + numOfVote <= pq.peek()) {
            int poll = pq.poll();
            numOfVote += 1;
            pq.offer(poll - 1);
        }
        return numOfVote;
    }

//    @Test
//    public void test() {
//        Assert.assertEquals(4, vote(new int[]{5, 1, 11, 2, 8}));
//        Assert.assertEquals(6, vote(new int[]{1, 8, 8, 8}));
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] votes = new int[n];
            for (int i = 0; i < n; i++) {
                votes[i] = scanner.nextInt();
            }
            System.out.println(vote(votes));
        }
    }
}
