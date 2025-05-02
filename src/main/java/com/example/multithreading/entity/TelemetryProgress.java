package com.example.multithreading.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
public class TelemetryProgress {
    private final AtomicInteger current = new AtomicInteger();
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
