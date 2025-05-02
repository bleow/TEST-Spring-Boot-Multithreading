package com.example.multithreading.entity;

import lombok.Builder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Builder
public class ChildTelemetry {
    @Builder.Default
    private final List<Job> completedJobs = new CopyOnWriteArrayList<>();

    public void addCompletedJob(Job job) {
        completedJobs.add(job);
    }
    public int getCompletedJobs() {
        return completedJobs.size();
    }
}
