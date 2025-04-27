package com.example.multithreading.repository;

import com.example.multithreading.entity.Telemetry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends MongoRepository<Telemetry, String> {
}
