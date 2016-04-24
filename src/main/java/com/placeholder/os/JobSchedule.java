package com.placeholder.os;

import java.util.*;

public class JobSchedule {


    static final int TIME_SLICE = 1;

    // Fist In First Out
    // First Come First Serve
    public static List<Job> FIFO(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> candidateJobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            candidateJobs.add(job);
        }
        List<Job> allJobs = new ArrayList<>(candidateJobs);

        int timePoint = 0;
        Queue<Job> scheduleQueue = new LinkedList<>();
        while (true) {
            Iterator<Job> iterator = candidateJobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.arrival <= timePoint) {
                    scheduleQueue.add(job);
                    iterator.remove();
                }
            }
            if (scheduleQueue.isEmpty()) {
                timePoint += 1;
            } else {
                Job currentJob = scheduleQueue.poll();
                if (currentJob.firstrun != -1)
                    currentJob.firstrun = timePoint;
                timePoint += currentJob.duration;
                currentJob.completed = timePoint;
            }
            if (scheduleQueue.isEmpty() && candidateJobs.isEmpty()) {
                break;
            }
        }
        return allJobs;
    }

    // Optimize turnaround time
    // Shorted Job First
    public static List<Job> SJF(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> candidateJobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            candidateJobs.add(job);
        }
        List<Job> allJobs = new ArrayList<>(candidateJobs);

        int timePoint = 0;
        Queue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.duration != o2.duration) {
                    return o1.duration - o2.duration;
                }
                return o1.arrival - o2.arrival;
            }
        });
        while (true) {
            Iterator<Job> iterator = candidateJobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.arrival <= timePoint) {
                    pq.offer(job);
                    iterator.remove();
                }
            }
            if (pq.isEmpty()) {
                timePoint += 1;
            } else {
                Job currentJob = pq.poll();
                if (currentJob.firstrun == -1)
                    currentJob.firstrun = timePoint;
                timePoint += currentJob.duration;
                currentJob.completed = timePoint;
            }
            if (pq.isEmpty() && candidateJobs.isEmpty()) {
                break;
            }
        }
        return allJobs;
    }

    // Optimize response time
    // Round Robin
    public static List<Job> RoundRobin(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> candidateJobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            candidateJobs.add(job);
        }
        List<Job> allJobs = new ArrayList<>(candidateJobs);

        int timePoint = 0;
        Queue<Job> scheduleQueue = new LinkedList<>();
        while (true) {
            Iterator<Job> iterator = candidateJobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.arrival <= timePoint) {
                    scheduleQueue.add(job);
                    iterator.remove();
                }
            }
            if (scheduleQueue.isEmpty()) {
                timePoint += TIME_SLICE;
            } else {
                Job currentJob = scheduleQueue.poll();
                if (currentJob.firstrun == -1)
                    currentJob.firstrun = timePoint;
                timePoint += TIME_SLICE;
                currentJob.duration -= TIME_SLICE;
                if (currentJob.duration <= 0) {
                    currentJob.completed = timePoint;
                } else {
                    scheduleQueue.offer(currentJob);
                }
            }
            if (scheduleQueue.isEmpty() && candidateJobs.isEmpty()) {
                break;
            }
        }
        return allJobs;
    }

    // Optimize turnaround time
    // Shortest Time-to-Completion First
    // Preemptive Shortest Job First (PSJF)
    public static List<Job> STCF(int[] requestTimes, int[] durations) {
        int n = requestTimes.length;
        List<Job> candidateJobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Job job = new Job(requestTimes[i], durations[i]);
            candidateJobs.add(job);
        }
        List<Job> allJobs = new ArrayList<>(candidateJobs);

        int timePoint = 0;
        Queue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.duration != o2.duration) {
                    return o1.duration - o2.duration;
                }
                return o1.arrival - o2.arrival;
            }
        });
        while (true) {
            Iterator<Job> iterator = candidateJobs.iterator();
            while (iterator.hasNext()) {
                Job job = iterator.next();
                if (job.arrival <= timePoint) {
                    pq.offer(job);
                    iterator.remove();
                }
            }
            if (pq.isEmpty()) {
                timePoint += 1;
            } else {
                Job currentJob = pq.poll();
                if (currentJob.firstrun == -1)
                    currentJob.firstrun = timePoint;
                timePoint += 1;
                currentJob.duration -= 1;
                if (currentJob.duration != 0) {
                    pq.offer(currentJob);
                } else {
                    currentJob.completed = timePoint;
                }
            }
            if (pq.isEmpty() && candidateJobs.isEmpty()) {
                break;
            }
        }
        return allJobs;
    }

    public static float turnaround(List<Job> jobs) {
        float turnaround = 0.0f;
        for (Job job : jobs) {
            turnaround += job.completed - job.arrival;
        }
        return turnaround * 1.0f / jobs.size();
    }

    public static float response(List<Job> jobs) {
        float turnaround = 0.0f;
        for (Job job : jobs) {
            turnaround += job.firstrun - job.arrival;
        }
        return turnaround * 1.0f / jobs.size();
    }


    public static void main(String[] args) {
        System.out.println(turnaround(STCF(new int[]{0, 10, 10}, new int[]{100, 10, 10})) + "[50.0]");
        System.out.println(turnaround(FIFO(new int[]{0, 10, 10}, new int[]{100, 10, 10})) + "[103.333336]");
        System.out.println(turnaround(RoundRobin(new int[]{0, 10, 10}, new int[]{100, 10, 10})) + "[59.666668]");
        System.out.println(response(STCF(new int[]{0, 10, 10}, new int[]{100, 10, 10})) + "[3.3333333]");
        System.out.println(response(RoundRobin(new int[]{0, 10, 10}, new int[]{100, 10, 10})) + "[1.0]");
    }

    static class Job {

        int arrival;   // arrival time
        int duration;
        int completed; // completed time
        int firstrun = -1;  //

        public Job(int arrival, int duration) {
            this.arrival = arrival;
            this.duration = duration;
        }
    }
}
