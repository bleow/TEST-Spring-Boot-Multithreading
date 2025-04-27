package com.example.multithreading.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mongo")
@Getter
@Setter //for Spring Boot to set the property. to fix: Reason: java.lang.IllegalStateException: No setter found for property: connection-string
public class MongoProperties {
    private String connectionString;
    private String databaseString;
}
