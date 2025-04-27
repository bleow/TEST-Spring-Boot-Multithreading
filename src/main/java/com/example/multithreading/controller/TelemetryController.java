package com.example.multithreading.controller;

import com.example.multithreading.entity.Telemetry;
import com.example.multithreading.repository.TelemetryRepository;
import com.example.multithreading.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
@Slf4j
public class TelemetryController {
    private final TelemetryRepository telemetryRepository;
    private final TelemetryService telemetryService;

    @PostMapping
    public Telemetry initialiseTelemetry() {
        log.info("Saving new object");
        Telemetry telemetry = Telemetry.builder().build();
        Telemetry res = telemetryRepository.insert(telemetry);
        log.info("Saved new object: {}", res);
        return res;
    }
}
