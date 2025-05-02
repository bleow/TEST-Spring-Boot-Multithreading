package com.example.multithreading.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Job {

    @Builder.Default
    private String jobName = "foo";

    @Builder.Default
    private LocalDateTime jobDate = LocalDateTime.now();

    @Builder.Default
    private Stats jobStats = Stats.builder().build();

    private JobType jobType;
}
