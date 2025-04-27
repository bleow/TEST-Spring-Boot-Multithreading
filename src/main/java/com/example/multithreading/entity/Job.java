package com.example.multithreading.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Job {
    private String jobName;
    private LocalDateTime jobDate;
    private Stats jobStats;
}
