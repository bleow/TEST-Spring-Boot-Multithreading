package com.example.multithreading.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Document("Telemetry")
@Data
@Builder
@AllArgsConstructor
public class SharedTelemetry {
    @Id
    private String id;

    @Builder.Default
    private final AtomicReference<TelemetryProgress> totalProgress = new AtomicReference<>(new TelemetryProgress());
    //thread-safe
    public void incrementTotalProgress() {
        this.totalProgress.get().incrementProgress();
    }

    @Builder.Default
    private final AtomicReference<Stats> totalStats = new AtomicReference<>(new Stats());
    //thread-safe
    public void updateTotalStats(Stats other) {
        this.totalStats.get().sumWith(other);
    }

    @Builder.Default
    private final AtomicReference<String> current = new AtomicReference<>();

    @Builder.Default
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
