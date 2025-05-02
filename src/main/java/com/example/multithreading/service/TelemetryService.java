package com.example.multithreading.service;

import com.example.multithreading.entity.SharedTelemetry;
import com.example.multithreading.entity.Stats;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class TelemetryService {
    // holds the raw object
    private SharedTelemetry sharedTelemetry;

    public void initialiseTelemetry() {
        SharedTelemetry obj = SharedTelemetry.builder()
                .id("foo")
                .build();
        sharedTelemetry = obj;
    }

    // calls the actual atomic function
    public void update(Stats other) {
        this.sharedTelemetry.updateTotalStats(other);
    }
}
