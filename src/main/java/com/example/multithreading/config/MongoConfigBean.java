package com.example.multithreading.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@RequiredArgsConstructor
public class MongoConfigBean {

    private final MongoProperties mongoProperties;


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoProperties.getConnectionString());
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, mongoProperties.getDatabaseString());
    }
}
