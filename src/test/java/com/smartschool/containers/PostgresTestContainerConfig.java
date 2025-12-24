package com.smartschool.containers;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.postgresql.PostgreSQLContainer;

public class PostgresTestContainerConfig implements BeforeAllCallback {

    private AtomicBoolean containerStarted = new AtomicBoolean(false);
    private PostgreSQLContainer postgreSQLContainer;


    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (!containerStarted.get()) {
            postgreSQLContainer = new PostgreSQLContainer("postgres:latest");
            postgreSQLContainer
                .withExposedPorts(5432)
                .start();

            System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
            System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
            System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
            containerStarted.set(true);
        }
    }
}
