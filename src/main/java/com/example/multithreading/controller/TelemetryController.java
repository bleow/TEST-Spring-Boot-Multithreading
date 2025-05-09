package com.example.multithreading.controller;

import com.example.multithreading.entity.*;
import com.example.multithreading.repository.TelemetryRepository;
import com.example.multithreading.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
@Slf4j
public class TelemetryController {
    private final TelemetryRepository telemetryRepository;
    private final TelemetryService telemetryService;

    @PostMapping
    public SharedTelemetry initialiseTelemetry() {
        log.info("Saving new object");
        SharedTelemetry sharedTelemetry = SharedTelemetry.builder().build();
        SharedTelemetry res = telemetryRepository.insert(sharedTelemetry);
        log.info("Saved new object: {}", res);
        return res;
    }

    @GetMapping
    public TelemetryProgress testIncrementThreadSafety() throws InterruptedException {
        Random rand = new Random();

        int THREAD_COUNT = rand.nextInt(100);
        int INC_PER_THREAD = rand.nextInt(1000);

        SharedTelemetry sharedTelemetry = SharedTelemetry.builder().build();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < INC_PER_THREAD; j++) {
                    sharedTelemetry.incrementTotalProgress();
                }
                latch.countDown(); //declare thread as finished running
            });
        }

        latch.await(); // wait for all threads to finish
        executor.shutdown();

        int expectedResult = THREAD_COUNT * INC_PER_THREAD;
        int actualResult = sharedTelemetry.getTotalProgress().get().getTotal().get();
        if (actualResult != expectedResult) {
            throw new IllegalStateException("Thread safety violated: expected " + expectedResult + ", got " + actualResult);
        }

        //both of these work
        return TelemetryProgress.builder()
                .current(new AtomicInteger(actualResult))
                .total(new AtomicInteger(expectedResult))
                .build();
//        TelemetryProgress res = new TelemetryProgress();
//        res.setCurrent(actualResult);
//        res.setTotal(expectedResult);
//        return res;
    }

    @PatchMapping
    public TelemetryProgress testUpdateTotalStatsThreadSafety() throws InterruptedException {
        telemetryService.initialiseTelemetry();

        Random rand = new Random();

        int THREAD_COUNT = rand.nextInt(100);
        int INC_PER_THREAD = rand.nextInt(1000);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < INC_PER_THREAD; j++) {
                    Job job = Job.builder().jobType(JobType.CD).build();

                    telemetryService.addToChild(job);

//                    System.out.println("hiii");

//                    telemetryService.addJob(job);

//                    Stats stats = new Stats();
//                    stats.setFilesAdded(1);
//                    stats.setFilesDeleted(1);
//                    stats.setFoldersAdded(1);
//                    stats.setFoldersDeleted(1);
//                    telemetryService.update(stats); //call the wrapper
                }
                latch.countDown(); //declare thread as finished running
            });
        }
        System.out.println("bro");

        latch.await(); // wait for all threads to finish
        executor.shutdown();

        int expectedResult = THREAD_COUNT * INC_PER_THREAD;
        int actualResult = telemetryService.getChildJobLen();

//        int actualResult = telemetryService.getJobLen();

//        int actualResult = telemetryService.getSharedTelemetry().getTotalStats().getFilesAdded();
//        int actualResult2 = telemetryService.getSharedTelemetry().getTotalStats().getFilesDeleted();
//        int actualResult3 = telemetryService.getSharedTelemetry().getTotalStats().getFoldersAdded();
//        int actualResult4 = telemetryService.getSharedTelemetry().getTotalStats().getFoldersDeleted();
//        if (actualResult != actualResult2 || actualResult != actualResult3 || actualResult != actualResult4) {
//            throw new IllegalStateException("Thread safety violated: expected " + expectedResult + ", got " + actualResult);
//        }
        if (actualResult != expectedResult) {
            throw new IllegalStateException("Thread safety violated: expected " + expectedResult + ", got " + actualResult);
        }

        //both of these work
        return TelemetryProgress.builder()
                .current(new AtomicInteger(actualResult))
                .total(new AtomicInteger(expectedResult))
                .build();
//        TelemetryProgress res = new TelemetryProgress();
//        res.setCurrent(actualResult);
//        res.setTotal(expectedResult);
//        return res;
    }
}
