package com.placeholder;

import java.util.*;

public class JobSchedule {

    // 轮训
    public static float RoundRobin(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            jobs.add(job);
        }

        int timePoint = 0;
        List<Job> scheduleQueue = new ArrayList<>();
        while (true) {
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.requestTime <= timePoint) {
                    scheduleQueue.add(job);
                    iterator.remove();
                }
            }
            if (scheduleQueue.isEmpty()) {
                timePoint += 1;
            } else {
                Job currentJob = scheduleQueue.get(0);
                timePoint += 1;
                currentJob.duration -= 1;
                scheduleQueue.remove(currentJob);
                if (currentJob.duration != 0) {
                    scheduleQueue.add(currentJob);
                }
            }
            if (scheduleQueue.isEmpty() && jobs.isEmpty()) {
                break;
            }
        }
        return timePoint * 1.0f / n;
    }

    // First Come First Serve
    public static float FCFS(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            jobs.add(job);
        }

        int timePoint = 0;
        List<Job> scheduleQueue = new ArrayList<>();
        while (true) {
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.requestTime <= timePoint) {
                    scheduleQueue.add(job);
                    iterator.remove();
                }
            }
            if (scheduleQueue.isEmpty()) {
                timePoint += 1;
            } else {
                Job currentJob = scheduleQueue.get(0);
                currentJob.serviceTime = timePoint;
                timePoint += currentJob.duration;
                scheduleQueue.remove(currentJob);
            }
            if (scheduleQueue.isEmpty() && jobs.isEmpty()) {
                break;
            }
        }
        return timePoint * 1.0f / n;
    }

    // 最短作业优先
    public static float ShortestJobFirst(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            jobs.add(job);
        }
        jobs.sort((o1, o2) -> o1.requestTime - o2.requestTime);  // 根据到达时间排序

        int timePoint = 0;
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.duration != o2.duration) {
                    return o1.duration - o2.duration;
                }
                return o1.requestTime - o2.requestTime;
            }
        });
        while (true) {
            Iterator<Job> iterator = jobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.requestTime <= timePoint) {
                    pq.offer(job);
                    iterator.remove();
                }
            }
            if (pq.isEmpty()) {
                timePoint += 1;
            } else {
                Job currentJob = pq.poll();
                currentJob.serviceTime = timePoint;
                timePoint += currentJob.duration;
            }
            if (pq.isEmpty() && jobs.isEmpty()) {
                break;
            }
        }
        return timePoint * 1.0f / n;
    }

    public static void main(String[] args) {
        System.out.println(ShortestJobFirst(new int[]{0, 2, 4, 5}, new int[]{7, 4, 1, 4}) + "[4.0]");
        System.out.println(ShortestJobFirst(new int[]{0, 1, 3, 9}, new int[]{2, 1, 7, 5}) + "[0.5]");

        System.out.println(FCFS(new int[]{0, 2, 4, 5}, new int[]{7, 4, 1, 4}) + "[4.0]");
        System.out.println(FCFS(new int[]{0, 1, 3, 9}, new int[]{2, 1, 7, 5}) + "[0.5]");

        System.out.println(RoundRobin(new int[]{0, 2, 4, 5}, new int[]{7, 4, 1, 4}) + "[4.0]");
        System.out.println(RoundRobin(new int[]{0, 1, 3, 9}, new int[]{2, 1, 7, 5}) + "[0.5]");
    }

    static class Job {
        int requestTime;
        int duration;
        int serviceTime;

        public Job(int requestTime, int duration) {
            this.requestTime = requestTime;
            this.duration = duration;
        }
    }
}
