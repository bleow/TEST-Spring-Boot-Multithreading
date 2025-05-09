package com.example.multithreading.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryProgress {
    @Builder.Default
    private final AtomicInteger current = new AtomicInteger();
    @Builder.Default
    private final AtomicInteger total = new AtomicInteger();

    public void incrementProgress() {
        this.total.incrementAndGet();
    }

    public int getCurrent() {
        return current.get();
    }

    public void setTotal(int value) {
        this.total.set(value); // thread-safe set
    }

    public void setCurrent(int value) {
        this.current.set(value); // also thread-safe if needed
    }
}
