package com.example.multithreading.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Document("Telemetry")
@Data
@Builder
public class Telemetry {
    private final AtomicReference<Stats> totalStats = new AtomicReference<>();
    private final AtomicReference<String> current = new AtomicReference<>();
    private final List<Job> completedJobs = new CopyOnWriteArrayList<>();

    // totalStats
    public Stats getTotalStats() {
        return totalStats.get();
    }

    public void setTotalStats(Stats stats) {
        totalStats.set(stats);
    }

    // current
    public String getCurrent() {
        return current.get();
    }

    public void setCurrent(String value) {
        current.set(value);
    }

    // completedJobs
    public void addCompletedJob(Job job) {
        completedJobs.add(job);
    }

    public List<Job> getCompletedJobs() {
        return List.copyOf(completedJobs); // immutable copy
    }

    public void clearCompletedJobs() {
        completedJobs.clear();
    }
}
